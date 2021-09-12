package com.az.flight.connector.builder.validator.impl;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.exception.CustomRuntimeException;
import com.az.flight.connector.builder.util.Constants;
import com.az.flight.connector.builder.validator.FlightConnectionValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FlightConnectionValidatorImpl implements FlightConnectionValidator {

    @Override
    public void validateFlightConnectionBuilderRequest(FlightConnectionBuilderRequest request) {
        validateDepartureAirportCode(request.getDepartureAirport());
        validateArrivalAirportCode(request.getArrivalAirport());
    }

    private void validateArrivalAirportCode(String arrivalAirport) {
        if(arrivalAirport == null)
            throw new CustomRuntimeException(Constants.ARRIVAL_CODE_ERROR_MSG, HttpStatus.BAD_REQUEST);
        validateAirportCode(arrivalAirport, Constants.ARRIVAL_CODE_ERROR_MSG);
    }

    private void validateDepartureAirportCode(String departureAirport) {
        if(departureAirport == null)
            throw new CustomRuntimeException(Constants.DEPARTURE_CODE_ERROR_MSG, HttpStatus.BAD_REQUEST);
        validateAirportCode(departureAirport, Constants.DEPARTURE_CODE_ERROR_MSG);
    }

    private void validateAirportCode(String airportCode, String msg) {
        if(!StringUtils.hasText(airportCode) || airportCode.length() != 3)
            throw new CustomRuntimeException(msg, HttpStatus.BAD_REQUEST);
    }
}
