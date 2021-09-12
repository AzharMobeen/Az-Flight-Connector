package com.az.flight.connector.builder.service;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.dto.FlightConnectionBuilderResponse;

public interface FlightConnectionBuilderService {

    FlightConnectionBuilderResponse populateOndWiseFlightConnections(FlightConnectionBuilderRequest request);
}
