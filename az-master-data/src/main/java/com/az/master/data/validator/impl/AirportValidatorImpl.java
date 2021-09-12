package com.az.master.data.validator.impl;

import com.az.master.data.entity.AirportEntity;
import com.az.master.data.exception.CustomRuntimeException;
import com.az.master.data.validator.AirportValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AirportValidatorImpl implements AirportValidator {

    @Override
    public void validateAddAirportRequest(AirportEntity airportEntity) {
        validateAirportEntity(airportEntity);
    }

    @Override
    public void validateAirportCode(String airportCode) {
        if(!StringUtils.hasText(airportCode) || airportCode.length() != 3)
            throw new CustomRuntimeException("Invalid Airport code", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void validateUpdateAirportRequest(AirportEntity airportEntity) {
        validateAirportEntity(airportEntity);
    }

    private void validateAirportEntity(AirportEntity airportEntity) {
        validateAirportCode(airportEntity.getAirportCode());
        validateAirportName(airportEntity.getAirportName());
        validateCityName(airportEntity.getCityName());
        validateCoordinates(airportEntity.getCoordinates());
    }

    private void validateCityName(String cityName) {
        if(!StringUtils.hasText(cityName))
            throw new CustomRuntimeException("Invalid airport city name", HttpStatus.BAD_REQUEST);
    }

    private void validateAirportName(String airportName) {
        if(!StringUtils.hasText(airportName))
            throw new CustomRuntimeException("Invalid airport name", HttpStatus.BAD_REQUEST);
    }

    private void validateCoordinates(String coordinates) {
        if(coordinates == null)
            throw new CustomRuntimeException("Invalid coordinates", HttpStatus.BAD_REQUEST);
        String[] array = coordinates.split(" ");
        if(array.length != 2)
            throw new CustomRuntimeException("Invalid coordinates", HttpStatus.BAD_REQUEST);
    }
}
