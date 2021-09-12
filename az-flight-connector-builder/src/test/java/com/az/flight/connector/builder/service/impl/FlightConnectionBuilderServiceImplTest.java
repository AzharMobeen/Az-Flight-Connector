package com.az.flight.connector.builder.service.impl;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.exception.CustomRuntimeException;
import com.az.flight.connector.builder.repository.FlightScheduleRepository;
import com.az.flight.connector.builder.util.TestUtil;
import com.az.flight.connector.builder.validator.impl.FlightConnectionValidatorImpl;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;

class FlightConnectionBuilderServiceImplTest {

    @InjectMocks
    FlightConnectionBuilderServiceImpl flightConnectionBuilderService;

    @Mock
    FlightConnectionValidatorImpl flightConnectionValidator;

    @Mock
    FlightScheduleRepository flightScheduleRepository;

    private FlightConnectionBuilderRequest request;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        request = new FlightConnectionBuilderRequest();
        Mockito.doNothing().when(flightConnectionValidator).validateFlightConnectionBuilderRequest(request);
    }

    @Test
    @DisplayName("For onward Flights exception")
    void populateOndWiseFlightConnectionsOnwardFlightException() {
        Mockito.when(flightScheduleRepository.findByDepartureAirport("ABC")).thenReturn(Collections.emptyList());
        assertThrows(CustomRuntimeException.class, () -> flightConnectionBuilderService
                .populateOndWiseFlightConnections(request), "No Onward flights available");
    }

    @Test
    @DisplayName("For connection Flights exception")
    void populateOndWiseFlightConnectionsConnectionFlightException() {
        request.setDepartureAirport("BOM");
        Mockito.when(flightScheduleRepository.findByDepartureAirport("BOM")).thenReturn(TestUtil.buildOnwardFlightList());
        Mockito.when(flightScheduleRepository.findByArrivalAirportAndDepartureAirportIn("JFK",
                Collections.singletonList("DXB"))).thenReturn(null);
        assertThrows(CustomRuntimeException.class, () -> flightConnectionBuilderService
                .populateOndWiseFlightConnections(request), "No Connection flights available");
    }

    @Test
    @DisplayName("For connection Flights exception")
    void populateOndWiseFlightConnections() {
        Mockito.when(flightScheduleRepository.findByDepartureAirport("BOM"))
                .thenReturn(TestUtil.buildOnwardFlightList());
        Mockito.when(flightScheduleRepository.findByArrivalAirportAndDepartureAirportIn("JFK",
                Collections.singletonList("DXB"))).thenReturn(TestUtil.buildConnectionFlightList());

        assertDoesNotThrow(() -> flightConnectionBuilderService.populateOndWiseFlightConnections(
                TestUtil.buildFlightConnectionBuilderRequest()));
    }
}