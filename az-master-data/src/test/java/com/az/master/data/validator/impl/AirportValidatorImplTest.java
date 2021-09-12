package com.az.master.data.validator.impl;

import com.az.master.data.entity.AirportEntity;
import com.az.master.data.exception.CustomRuntimeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AirportValidatorImplTest {

    @InjectMocks
    AirportValidatorImpl airportValidator;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateAddAirportRequest() {
        AirportEntity request = new AirportEntity();
        request.setAirportCode("");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateAddAirportRequest(request),
                "Invalid Airport Code");

        request.setAirportCode("LHEE");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateAddAirportRequest(request),
                "Invalid Airport Code");

        request.setAirportCode("LHE");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateAddAirportRequest(request),
                "Invalid Airport Name");
        request.setAirportName("Lahore International airport");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateAddAirportRequest(request),
                "Invalid airport city name");
        request.setCityName("Lahore");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateAddAirportRequest(request),
                "Invalid coordinates");
        request.setCoordinates("25°15′10″N");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateAddAirportRequest(request),
                "Invalid coordinates");
        request.setCoordinates("25°15′10″N 055°21′52″E");
        assertDoesNotThrow(() -> airportValidator.validateAddAirportRequest(request));
    }

    @Test
    void validateAirportCode() {
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateAirportCode(""),
                "Invalid Airport code");
    }

    @Test
    void validateUpdateAirportRequest() {
        AirportEntity request = new AirportEntity();
        request.setAirportCode("");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateUpdateAirportRequest(request),
                "Invalid Airport Code");

        request.setAirportCode("LHEE");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateUpdateAirportRequest(request),
                "Invalid Airport Code");

        request.setAirportCode("LHE");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateUpdateAirportRequest(request),
                "Invalid Airport Name");
        request.setAirportName("Lahore International airport");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateUpdateAirportRequest(request),
                "Invalid airport city name");
        request.setCityName("Lahore");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateUpdateAirportRequest(request),
                "Invalid coordinates");
        request.setCoordinates("25°15′10″N");
        assertThrows(CustomRuntimeException.class,() -> airportValidator.validateUpdateAirportRequest(request),
                "Invalid coordinates");
        request.setCoordinates("25°15′10″N 055°21′52″E");
        assertDoesNotThrow(() -> airportValidator.validateUpdateAirportRequest(request));

    }
}