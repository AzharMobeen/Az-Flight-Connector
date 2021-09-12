package com.az.flight.connector.builder.entity;


import java.time.LocalTime;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight_schedule")
@Data
public class FlightSchedule {

    @Id
    private String flightNumber;

    private String departureAirport;

    private String arrivalAirport;

    private LocalTime departureTime;

    private LocalTime arrivalTime;
}
