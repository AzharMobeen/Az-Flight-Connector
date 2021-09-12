package com.az.master.data.service;

import com.az.master.data.entity.AirportEntity;
import java.util.List;

public interface AirportService {

    AirportEntity addAirport(AirportEntity airportEntity);
    AirportEntity getAirportByCode(String airportCode);
    List<AirportEntity> fetchAllAirports();
    AirportEntity updateAirport(AirportEntity airportEntity);
    void deleteAirport(String airportCode);
}
