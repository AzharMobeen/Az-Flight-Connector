package com.az.flight.connector.builder.validator;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;

public interface FlightConnectionValidator {
    void validateFlightConnectionBuilderRequest(FlightConnectionBuilderRequest request);
}
