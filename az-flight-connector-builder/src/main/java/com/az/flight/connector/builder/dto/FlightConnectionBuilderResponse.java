package com.az.flight.connector.builder.dto;


import java.util.List;
import lombok.Data;

@Data
public class FlightConnectionBuilderResponse {
    private List<ConnectionData> flightScheduleList;
}
