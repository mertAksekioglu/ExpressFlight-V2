package com.expressflight.ExpressFlight.microservice;

import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.repository.IAirportRepository;
import com.expressflight.ExpressFlight.requestdto.FlightRequestDTO;
import com.expressflight.ExpressFlight.requestdto.FlightSearchRequestDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightDataGenerationService {

    private IAirportRepository airportRepository;


    public void generateFlightDataForTheDay() {


    }

    public void generateTurkishAirlinesFlight(FlightSearchRequestDTO requestedParameters, int flightDurationInMins) {
        FlightRequestDTO f = new FlightRequestDTO();
        f.setAirline("Turkish Airlines");
        f.setPrice(generateFlightPrice(1700,2000,10,true));
        f.setDepAirport(airportRepository.findByCodeIATA(requestedParameters.getDepAirport()).get().getId());
        f.setArvAirport(airportRepository.findByCodeIATA(requestedParameters.getDesAirport()).get().getId());
        List<LocalDateTime> depAndArvTimes = scheduleFlightTimes(requestedParameters.getDepDate(), flightDurationInMins);
        f.setDepDateTime(depAndArvTimes.get(0));
        f.setArvDateTime(depAndArvTimes.get(1));
        f.setSeatConfig(null);
    }

    public void generatePegasusFlight(FlightSearchRequestDTO requestedParameters) {
        FlightRequestDTO f = new FlightRequestDTO();
        f.setAirline("Pegasus Airlines");
        f.setPrice(generateFlightPrice(1000,1300,10,true));
    }

    public void generateSunExpressFlight(FlightSearchRequestDTO requestedParameters) {
        FlightRequestDTO f = new FlightRequestDTO();
        f.setAirline("SunExpress Airlines");
        f.setPrice(generateFlightPrice(1000,1300,10,true));
    }



    private Double generateFlightPrice(int min, int max, int step, boolean endsWith99) {
        Random random = new Random();
        double randomNumber = min + (random.nextInt((max - min) / step + 1) * step);
        if(endsWith99) {
            return randomNumber + 9.99;
        }
        else {
            return randomNumber;
        }

    }


    public static List<LocalDateTime> scheduleFlightTimes(LocalDate date, int flightDurationInMinutes) {
        List<LocalDateTime> flightTimes = new ArrayList<>();

        // Get the start and end times for the given date
        LocalDateTime startDateTime = date.atTime(LocalTime.MIN);
        LocalDateTime endDateTime = date.atTime(LocalTime.MAX);

        // Calculate the maximum possible departure time
        LocalDateTime maxDepartureTime = endDateTime.minusMinutes(flightDurationInMinutes);

        // Generate a random departure time within the valid range
        Random random = new Random();
        int departureMinute = random.nextInt(maxDepartureTime.getHour() * 60 + maxDepartureTime.getMinute() + 1);
        LocalDateTime departureDateTime = startDateTime.plusMinutes(departureMinute);

        // Calculate the arrival time by adding the flight duration
        LocalDateTime arrivalDateTime = departureDateTime.plusMinutes(flightDurationInMinutes);

        // Round departure and arrival times to the nearest 5-minute interval
        departureDateTime = roundTo5Minutes(departureDateTime);
        arrivalDateTime = roundTo5Minutes(arrivalDateTime);

        flightTimes.add(departureDateTime);
        flightTimes.add(arrivalDateTime);

        return flightTimes;
    }

    // Helper function to round a LocalDateTime to the nearest 5-minute interval
    private static LocalDateTime roundTo5Minutes(LocalDateTime dateTime) {
        int minute = dateTime.getMinute();
        int minuteRounded = (minute / 5) * 5; // Round down to the nearest 5 minutes
        return dateTime.withMinute(minuteRounded);
    }


}


/*

     private String airline;
    private String flightCode;
    private Double price;
    private Long depAirport;
    private Long arvAirport;
    private LocalDateTime depDateTime;
    private LocalDateTime arvDateTime;
    private Long seatConfig;

* */