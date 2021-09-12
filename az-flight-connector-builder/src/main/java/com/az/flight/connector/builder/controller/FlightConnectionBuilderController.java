package com.az.flight.connector.builder.controller;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.dto.FlightConnectionBuilderResponse;
import com.az.flight.connector.builder.service.FlightConnectionBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FlightConnectionBuilderController {

    private final FlightConnectionBuilderService flightConnectionBuilderService;

    @PostMapping("/flight/ondWiseConnectionFlights")
    public FlightConnectionBuilderResponse fetchOndWiseFlightScheduleList(@RequestBody
                                                                              FlightConnectionBuilderRequest request) {
        return flightConnectionBuilderService.populateOndWiseFlightConnections(request);
    }

}
