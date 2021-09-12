package com.az.flight.connector.builder.repository;

import com.az.flight.connector.builder.entity.FlightSchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, String> {

    // For Onward Flight
    List<FlightSchedule> findByDepartureAirport(String departureAirport);
    // For Connection Flight
    List<FlightSchedule> findByArrivalAirportAndDepartureAirportIn(String arrivalAirport, List<String> departureAirports);
}
