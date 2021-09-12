package com.az.master.data.service.impl;

import com.az.master.data.entity.AirportEntity;
import com.az.master.data.exception.CustomRuntimeException;
import com.az.master.data.repository.AirportRepository;
import com.az.master.data.service.AirportService;
import com.az.master.data.validator.AirportValidator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportValidator airportValidator;
    private final AirportRepository airportRepository;

    @Override
    public AirportEntity addAirport(AirportEntity airportEntity) {
        log.debug("addAirport request {}", airportEntity);
        airportValidator.validateAddAirportRequest(airportEntity);
        checkAirportCodeAlreadyExist(airportEntity.getAirportCode());
        return airportRepository.save(airportEntity);
    }

    private void checkAirportCodeAlreadyExist(String airportCode) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(airportCode);
        if(airportEntity.isPresent())
            throw new CustomRuntimeException("Airport code already exist", HttpStatus.CONFLICT);
    }

    @Override
    public AirportEntity getAirportByCode(String airportCode) {
        log.debug("getAirport By Code {}", airportCode);
        airportValidator.validateAirportCode(airportCode);
        Optional<AirportEntity> airportEntity = airportRepository.findById(airportCode);
        if(airportEntity.isPresent())
            return airportEntity.get();
        else{
            log.debug("Airport not found By Code");
            throw new CustomRuntimeException("Airport not found", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<AirportEntity> fetchAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public AirportEntity updateAirport(AirportEntity airportEntity) {
        log.debug("update Airport request {}", airportEntity);
        airportValidator.validateUpdateAirportRequest(airportEntity);
        validateAirportExist(airportEntity.getAirportCode());
        return airportRepository.save(airportEntity);
    }

    private void validateAirportExist(String airportCode) {
        Optional<AirportEntity> entity = airportRepository.findById(airportCode);
        if(!entity.isPresent())
            throw new CustomRuntimeException("Airport code doesn't exist", HttpStatus.NO_CONTENT);
    }

    @Override
    public void deleteAirport(String airportCode) {
        log.debug("delete Airport By Code {}", airportCode);
        validateAirportExist(airportCode);
        airportRepository.deleteById(airportCode);
    }
}
