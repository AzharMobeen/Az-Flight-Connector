package com.az.flight.connector.builder.controller;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.dto.FlightConnectionBuilderResponse;
import com.az.flight.connector.builder.service.impl.FlightConnectionBuilderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class FlightConnectionBuilderControllerTest {

    @InjectMocks
    FlightConnectionBuilderController flightConnectionBuilderController;

    @Mock
    FlightConnectionBuilderServiceImpl flightConnectionBuilderService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchOndWiseFlightScheduleList() {
        FlightConnectionBuilderRequest request = new FlightConnectionBuilderRequest();
        FlightConnectionBuilderResponse response = new FlightConnectionBuilderResponse();
        Mockito.when(flightConnectionBuilderService.populateOndWiseFlightConnections(request)).thenReturn(response);
        assertNotNull(flightConnectionBuilderController.fetchOndWiseFlightScheduleList(request));
    }
}