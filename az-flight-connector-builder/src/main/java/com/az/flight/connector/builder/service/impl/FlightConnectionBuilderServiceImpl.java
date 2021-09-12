package com.az.flight.connector.builder.service.impl;

import com.az.flight.connector.builder.dto.ConnectionData;
import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.dto.FlightConnectionBuilderResponse;
import com.az.flight.connector.builder.entity.FlightSchedule;
import com.az.flight.connector.builder.exception.CustomRuntimeException;
import com.az.flight.connector.builder.repository.FlightScheduleRepository;
import com.az.flight.connector.builder.service.FlightConnectionBuilderService;
import com.az.flight.connector.builder.validator.FlightConnectionValidator;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightConnectionBuilderServiceImpl implements FlightConnectionBuilderService {

    private final FlightConnectionValidator flightConnectionValidator;
    private final FlightScheduleRepository flightScheduleRepository;

    @Override
    public FlightConnectionBuilderResponse populateOndWiseFlightConnections(FlightConnectionBuilderRequest request) {
        log.debug("populateOndWiseFlightConnections calls with request {}", request);
        flightConnectionValidator.validateFlightConnectionBuilderRequest(request);
        List<FlightSchedule> onwardFlightList = flightScheduleRepository
                .findByDepartureAirport(request.getDepartureAirport());
        List<FlightSchedule> connectionFlightList = fetchAllConnectionFlights(onwardFlightList,
                request.getArrivalAirport());
        return generateFlightConnectionBuilderResponse(onwardFlightList, connectionFlightList);
    }

    private FlightConnectionBuilderResponse generateFlightConnectionBuilderResponse(
            List<FlightSchedule> onwardFlightList, List<FlightSchedule> connectionFlightList) {

        if(CollectionUtils.isEmpty(connectionFlightList))
            throw new CustomRuntimeException("No Connection flights available", HttpStatus.NO_CONTENT);

        List<ConnectionData> connectionDataList = new ArrayList<>();
        onwardFlightList.forEach(onwardFlight ->
                connectionFlightList.forEach(connFlight -> {
                  if(validateWaitingTime(onwardFlight.getArrivalTime(), connFlight.getDepartureTime()))
                      connectionDataList.add(buildConnectionData(onwardFlight, connFlight));
                })
        );
        FlightConnectionBuilderResponse response = new FlightConnectionBuilderResponse();
        response.setFlightScheduleList(connectionDataList);
        return response;
    }

    private ConnectionData buildConnectionData(FlightSchedule onwardFlight, FlightSchedule connFlight) {
        ConnectionData connectionData = new ConnectionData();
        // For Onward Flight
        connectionData.setOnwardFltNo(onwardFlight.getFlightNumber());
        connectionData.setOnwardDepArpt(onwardFlight.getDepartureAirport());
        connectionData.setOnwardArrArpt(onwardFlight.getArrivalAirport());
        connectionData.setOnwardDepTime(onwardFlight.getDepartureTime());
        connectionData.setOnwardArrTime(onwardFlight.getArrivalTime());
        // For Connection Flight
        connectionData.setConnFltNo(connFlight.getFlightNumber());
        connectionData.setConnDepArpt(connFlight.getDepartureAirport());
        connectionData.setConnArrArpt(connFlight.getArrivalAirport());
        connectionData.setConnDepTime(connFlight.getDepartureTime());
        connectionData.setConnArrTime(connFlight.getArrivalTime());

        return connectionData;
    }

    private boolean validateWaitingTime(LocalTime arrivalTime, LocalTime departureTime) {
        long waitingTime;
        if(arrivalTime.isBefore(departureTime))
            waitingTime = Duration.between(arrivalTime, departureTime).toMinutes();
        else {
            // In this case next day time needs to add 24 hr (1440 in minutes)
            waitingTime = Duration.between(arrivalTime,departureTime).toMinutes()+1440;
        }
        // I'm comparing with minutes (120 = 2hr, 480 = 8 hr)
        return waitingTime >= 120 && waitingTime <= 480;
    }

    private List<FlightSchedule> fetchAllConnectionFlights(List<FlightSchedule> onwardFlightList,
                                                           String arrivalAirport) {
        if(CollectionUtils.isEmpty(onwardFlightList))
            throw new CustomRuntimeException("No Onward flights available", HttpStatus.NO_CONTENT);

        List<String> connectAirportList = onwardFlightList.stream().map(FlightSchedule::getArrivalAirport)
                .distinct().collect(Collectors.toList());
        return flightScheduleRepository.findByArrivalAirportAndDepartureAirportIn(arrivalAirport, connectAirportList);
    }
}
