package com.az.master.data.controller;

import com.az.master.data.entity.AirportEntity;
import com.az.master.data.service.AirportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PostMapping("/airport")
    public AirportEntity createAirport(@RequestBody AirportEntity airportEntity) {
        return airportService.addAirport(airportEntity);
    }

    @GetMapping("/airport/{airportCode}")
    public AirportEntity getAirportByCode(@PathVariable String airportCode) {
        return airportService.getAirportByCode(airportCode);
    }

    @GetMapping("/airport/fetchAll")
    public List<AirportEntity> getAllAirports() {
        return airportService.fetchAllAirports();
    }

    @PutMapping("/airport")
    public AirportEntity updateAirport(@RequestBody AirportEntity airportEntity) {
        return airportService.updateAirport(airportEntity);
    }

    @DeleteMapping("/airport/{airportCode}")
    public String deleteAirport(@PathVariable String airportCode) {
        airportService.deleteAirport(airportCode);
        return "Airport successfully deleted";
    }

}
