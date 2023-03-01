package com.lunex.LunEx1.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lunex.LunEx1.deserializer.LocalDateDeserializer;
import com.lunex.LunEx1.deserializer.LocalTimeDeserializer;
import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.SunExpressFlightDTO;
import com.lunex.LunEx1.mapper.IMapper;
import com.lunex.LunEx1.mapper.SunExpressFlightDTOToFlightDTOMapper;
import com.lunex.LunEx1.provider.IProvider;
import com.lunex.LunEx1.provider.SunExpressFlightProvider;
import com.lunex.LunEx1.serializer.LocalDateSerializer;
import com.lunex.LunEx1.serializer.LocalTimeSerializer;
import com.lunex.LunEx1.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Component
public class SunExpressIntegration implements IIntegration {

    @Autowired
    private SunExpressFlightProvider sunExpressFlightProvider;
    private SunExpressFlightDTO[] SunExpressFlights;

    DateTimeFormatter dd_mm_yyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern("HH:mm");
    private String jsonString;

    private Gson gson;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private FlightService flightService;

    @Autowired
    private IMapper m;

    public SunExpressIntegration() {

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
            FlightDTO flightDto = (FlightDTO) m.map(SunExpressFlights[i],null);
            System.out.println(flightDto);
            flightService.addFlight(flightDto);
        }
        System.out.println(Arrays.toString(SunExpressFlights));
    }





}
