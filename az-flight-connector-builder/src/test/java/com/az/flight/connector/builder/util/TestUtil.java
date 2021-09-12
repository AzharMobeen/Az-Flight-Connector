package com.az.flight.connector.builder.util;

import com.az.flight.connector.builder.dto.FlightConnectionBuilderRequest;
import com.az.flight.connector.builder.entity.FlightSchedule;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TestUtil {

    public static List<FlightSchedule> buildOnwardFlightList() {
        return Collections.singletonList(buildFlightSchedule("EK 501", "BOM", "DXB", "04:30", "06:00"));
    }

    public static List<FlightSchedule> buildConnectionFlightList() {
        FlightSchedule correctOne = buildFlightSchedule("EK 201", "DXB", "JFK","08:30", "14:25");
        FlightSchedule wrongForMoreThan8Hrs = buildFlightSchedule("EK 201", "DXB", "JFK","02:50", "20:50");
        FlightSchedule wrongForLessThan2Hrs = buildFlightSchedule("EK 201", "DXB", "JFK","07:50", "10:50");
        return Arrays.asList(correctOne,wrongForMoreThan8Hrs, wrongForLessThan2Hrs);
    }

    private static FlightSchedule buildFlightSchedule( String flightNo, String from, String to, String deptTime,
                                                       String arrTime) {
        FlightSchedule flightSchedule = new FlightSchedule();
        flightSchedule.setFlightNumber(flightNo);
        flightSchedule.setDepartureAirport(from);
        flightSchedule.setArrivalAirport(to);
        flightSchedule.setDepartureTime(LocalTime.parse(deptTime));
        flightSchedule.setArrivalTime(LocalTime.parse(arrTime));
        return flightSchedule;
    }

    public static FlightConnectionBuilderRequest buildFlightConnectionBuilderRequest() {
        FlightConnectionBuilderRequest request = new FlightConnectionBuilderRequest();
        request.setDepartureAirport("BOM");
        request.setArrivalAirport("JFK");
        return request;
    }
}
