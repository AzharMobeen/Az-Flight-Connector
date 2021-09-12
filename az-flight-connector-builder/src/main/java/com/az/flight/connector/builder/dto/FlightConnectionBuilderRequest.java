package com.az.flight.connector.builder.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class FlightConnectionBuilderRequest {

    @JsonAlias("depAirport")
    private String departureAirport;
    @JsonAlias("arrAirport")
    private String arrivalAirport;
}
