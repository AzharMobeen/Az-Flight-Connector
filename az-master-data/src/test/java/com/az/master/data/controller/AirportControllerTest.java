package com.az.master.data.controller;

import com.az.master.data.entity.AirportEntity;
import com.az.master.data.service.impl.AirportServiceImpl;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AirportControllerTest {

    @InjectMocks
    AirportController airportController;

    @Mock
    AirportServiceImpl airportService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAirport() {
        AirportEntity request = new AirportEntity();
        Mockito.when(airportService.addAirport(request)).thenReturn(request);
        assertNotNull(airportController.createAirport(request));
    }

    @Test
    void getAirportByCode() {
        Mockito.when(airportService.getAirportByCode("LHE")).thenReturn(new AirportEntity());
        assertNotNull(airportController.getAirportByCode("LHE"));
    }

    @Test
    void getAllAirports() {
        Mockito.when(airportService.fetchAllAirports()).thenReturn(Collections.singletonList(new AirportEntity()));
        assertTrue(airportController.getAllAirports().size() > 0);
    }

    @Test
    void updateAirport() {
        Mockito.when(airportService.updateAirport(new AirportEntity())).thenReturn(new AirportEntity());
        assertNotNull(airportController.updateAirport(new AirportEntity()));
    }

    @Test
    void deleteAirport() {
        Mockito.doNothing().when(airportService).deleteAirport("LHE");
        assertEquals("Airport successfully deleted", airportController.deleteAirport("LHE"));
    }
}