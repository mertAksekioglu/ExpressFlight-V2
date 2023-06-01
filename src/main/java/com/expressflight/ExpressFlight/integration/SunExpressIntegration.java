package com.expressflight.ExpressFlight.integration;

import com.expressflight.ExpressFlight.provider.IProvider;
import com.expressflight.ExpressFlight.serviceInterface.IFlightService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.expressflight.ExpressFlight.deserializer.LocalDateDeserializer;
import com.expressflight.ExpressFlight.deserializer.LocalTimeDeserializer;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.dto.SunExpressFlightDTO;
import com.expressflight.ExpressFlight.mapper.IMapper;
import com.expressflight.ExpressFlight.provider.SunExpressFlightProvider;
import com.expressflight.ExpressFlight.serializer.LocalDateSerializer;
import com.expressflight.ExpressFlight.serializer.LocalTimeSerializer;
import com.expressflight.ExpressFlight.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Component
public class SunExpressIntegration implements IIntegration {

    private final DateTimeFormatter dd_mm_yyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern("HH:mm");

    private String jsonString;
    private SunExpressFlightDTO[] SunExpressFlights;
    private Gson gson;
    private IProvider sunExpressFlightProvider;
    private IFlightService flightService;
    private IMapper mapper;

    public SunExpressIntegration(IProvider sunExpressFlightProvider,IFlightService flightService,IMapper mapper) {
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
            FlightDTO flightDto = (FlightDTO) mapper.map(SunExpressFlights[i],null);
            System.out.println(flightDto);
            flightService.addFlight(flightDto);
        }
        System.out.println(Arrays.toString(SunExpressFlights));
    }





}
