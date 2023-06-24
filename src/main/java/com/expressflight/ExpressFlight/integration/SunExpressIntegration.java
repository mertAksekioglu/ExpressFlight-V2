package com.expressflight.ExpressFlight.integration;

import com.expressflight.ExpressFlight.deserializer.LocalDateDeserializer;
import com.expressflight.ExpressFlight.deserializer.LocalTimeDeserializer;
import com.expressflight.ExpressFlight.dtomapper.IDTOMapper;
import com.expressflight.ExpressFlight.provider.IProvider;
import com.expressflight.ExpressFlight.provider.SunExpressFlightDTO;
import com.expressflight.ExpressFlight.requestdto.FlightRequestDTO;
import com.expressflight.ExpressFlight.serializer.LocalDateSerializer;
import com.expressflight.ExpressFlight.serializer.LocalTimeSerializer;
import com.expressflight.ExpressFlight.serviceInterface.IFlightService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class SunExpressIntegration implements IIntegration {

    private final DateTimeFormatter dd_mm_yyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern("HH:mm");

    private IProvider sunExpressFlightProvider;

    private IFlightService flightService;

    private IDTOMapper mapper;

    private Gson gson;

    private String jsonString;

    private SunExpressFlightDTO[] SunExpressFlights;

    public SunExpressIntegration(IProvider sunExpressFlightProvider,IFlightService flightService,IDTOMapper mapper) {
        this.sunExpressFlightProvider = sunExpressFlightProvider;
        this.flightService = flightService;
        this.mapper = mapper;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer(dd_mm_yyyy))
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer(dd_mm_yyyy))
                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer(HH_mm))
                .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer(HH_mm))
                .setPrettyPrinting().create();
    }

    @Override
    public void getData() {
        jsonString = sunExpressFlightProvider.getData();
    }

    @Override
    public void addData() {
        var gson = this.gson;
        SunExpressFlights = gson.fromJson(jsonString, SunExpressFlightDTO[].class);
        for (int i = 0; i < SunExpressFlights.length; i++) {
            FlightRequestDTO flightRequestDto = (FlightRequestDTO) mapper.map(SunExpressFlights[i],null);
            flightService.addFlight(flightRequestDto);
        }
    }

}