package com.az.master.data.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
@Data
public class AirportEntity {

    @JsonAlias("arptCd")
    @Id
    private String airportCode;

    @JsonAlias("arptName")
    private String airportName;

    private String cityName;
    private String coordinates;

}
