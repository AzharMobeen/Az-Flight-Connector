package com.az.flight.connector.builder.dto;

import java.time.LocalTime;
import lombok.Data;

@Data
public class ConnectionData {
    
    private String onwardFltNo;
    private String onwardDepArpt;
    private String onwardArrArpt;
    private LocalTime onwardDepTime;
    private LocalTime onwardArrTime;
    private String connFltNo;
    private String connDepArpt;
    private String connArrArpt;
    private LocalTime connDepTime;
    private LocalTime connArrTime;
}
