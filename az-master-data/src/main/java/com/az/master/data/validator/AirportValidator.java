package com.az.master.data.validator;

import com.az.master.data.entity.AirportEntity;

public interface AirportValidator {
    void validateAddAirportRequest(AirportEntity airportEntity);

    void validateAirportCode(String airportCode);

    void validateUpdateAirportRequest(AirportEntity airportEntity);
}
