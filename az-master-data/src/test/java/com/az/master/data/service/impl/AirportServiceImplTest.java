package com.az.master.data.service.impl;

import com.az.master.data.entity.AirportEntity;
import com.az.master.data.exception.CustomRuntimeException;
import com.az.master.data.repository.AirportRepository;
import com.az.master.data.validator.impl.AirportValidatorImpl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AirportServiceImplTest {

    @InjectMocks
    AirportServiceImpl airportService;

    @Mock
    AirportValidatorImpl airportValidator;

    @Mock
    AirportRepository airportRepository;


    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addAirport() {
        AirportEntity request = new AirportEntity();
        request.setAirportCode("ABC");
        Mockito.doNothing().when(airportValidator).validateAddAirportRequest(request);
        Optional<AirportEntity> response = Optional.empty();
        Mockito.when(airportRepository.findById(request.getAirportCode())).thenReturn(response);
        Mockito.when(airportRepository.save(request)).thenReturn(new AirportEntity());
        assertNotNull(airportService.addAirport(request));

        response = Optional.of(request);
        Mockito.when(airportRepository.findById(request.getAirportCode())).thenReturn(response);
        assertThrows(CustomRuntimeException.class, () -> airportService.addAirport(request),
                "Airport code already exist");
    }

    @Test
    void getAirportByCode() {
        Mockito.doNothing().when(airportValidator).validateAirportCode("ABC");

        Optional<AirportEntity> airportEntity = Optional.empty();
        Mockito.when(airportRepository.findById("ABC")).thenReturn(airportEntity);
        assertThrows(CustomRuntimeException.class, () -> airportService.getAirportByCode("ABC"), "Airport not found");

        airportEntity = Optional.of(new AirportEntity());
        Mockito.when(airportRepository.findById("ABC")).thenReturn(airportEntity);
        assertNotNull(airportService.getAirportByCode("ABC"));
    }

    @Test
    void fetchAllAirports() {
        List<AirportEntity> airportEntityList = Collections.singletonList(new AirportEntity());
        Mockito.when(airportRepository.findAll()).thenReturn(airportEntityList);
        assertTrue(airportService.fetchAllAirports().size() > 0);
    }

    @Test
    void updateAirport() {
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setAirportCode("ABC");
        Mockito.doNothing().when(airportValidator).validateUpdateAirportRequest(airportEntity);
        Optional<AirportEntity> response = Optional.empty();
        Mockito.when(airportRepository.findById(airportEntity.getAirportCode())).thenReturn(response);
        assertThrows(CustomRuntimeException.class, () -> airportService.updateAirport(airportEntity),
                "Airport code doesn't exist");

        response = Optional.of(new AirportEntity());
        Mockito.when(airportRepository.findById(airportEntity.getAirportCode())).thenReturn(response);
        Mockito.when(airportRepository.save(airportEntity)).thenReturn(response.get());
        assertNotNull(airportService.updateAirport(airportEntity));
    }

    @Test
    void deleteAirport() {
        String airportCode = "ABC";
        Optional<AirportEntity> response = Optional.empty();
        Mockito.when(airportRepository.findById(airportCode)).thenReturn(response);
        assertThrows(CustomRuntimeException.class, () -> airportService.deleteAirport(airportCode),
                "Airport code doesn't exist");
        response = Optional.of(new AirportEntity());
        Mockito.when(airportRepository.findById(airportCode)).thenReturn(response);
        assertDoesNotThrow(() -> airportService.deleteAirport(airportCode));
    }
}