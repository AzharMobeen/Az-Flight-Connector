package com.az.flight.connector.builder.validator.impl;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.exception.CustomRuntimeException;
import com.az.flight.connector.builder.util.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class FlightConnectionValidatorImplTest {

    @InjectMocks
    FlightConnectionValidatorImpl flightConnectionValidator;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateFlightConnectionBuilderRequest() {
        FlightConnectionBuilderRequest request = new FlightConnectionBuilderRequest();
        assertThrows(CustomRuntimeException.class, () -> flightConnectionValidator
                .validateFlightConnectionBuilderRequest(request), Constants.DEPARTURE_CODE_ERROR_MSG);
        request.setDepartureAirport("ABCD");
        assertThrows(CustomRuntimeException.class, () -> flightConnectionValidator
                .validateFlightConnectionBuilderRequest(request), Constants.DEPARTURE_CODE_ERROR_MSG);
        request.setDepartureAirport("ABC");

        assertThrows(CustomRuntimeException.class, () -> flightConnectionValidator
                .validateFlightConnectionBuilderRequest(request), Constants.ARRIVAL_CODE_ERROR_MSG);
        request.setArrivalAirport("XYZZ");
        assertThrows(CustomRuntimeException.class, () -> flightConnectionValidator
                .validateFlightConnectionBuilderRequest(request), Constants.ARRIVAL_CODE_ERROR_MSG);

        request.setArrivalAirport("");
        assertThrows(CustomRuntimeException.class, () -> flightConnectionValidator
                .validateFlightConnectionBuilderRequest(request), Constants.ARRIVAL_CODE_ERROR_MSG);

        request.setArrivalAirport("XYZ");
        assertDoesNotThrow(() -> flightConnectionValidator.validateFlightConnectionBuilderRequest(request));
    }
}