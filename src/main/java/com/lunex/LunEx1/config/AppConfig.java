package com.lunex.LunEx1.config;


import com.google.gson.*;
import com.lunex.LunEx1.deserializer.LocalDateDeserializer;
import com.lunex.LunEx1.deserializer.LocalDateTimeDeserializer;
import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.domain.ConnectedFlight;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.repository.IAirportRepository;
import com.lunex.LunEx1.repository.IConnectedFlightRepository;
import com.lunex.LunEx1.repository.IFlightRepository;
import com.lunex.LunEx1.repository.IPlaneRepository;
import com.lunex.LunEx1.serializer.LocalDateSerializer;
import com.lunex.LunEx1.serializer.LocalDateTimeSerializer;
import com.lunex.LunEx1.util.JPARepoPopulator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
@Service
public class AppConfig {



    private DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern("HH:mm");
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer(yyyy_MM_dd_HH_mm_ss))
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer(yyyy_MM_dd_HH_mm_ss))
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer(yyyy_MM_dd))
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer(yyyy_MM_dd))
                .setPrettyPrinting().create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public JPARepoPopulator jpaRepoPopulator() {
        var gson = gson();
        return new JPARepoPopulator(gson);
    }

    @Autowired
    JPARepoPopulator populator;

    String resource_path = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\";


    @Bean
    CommandLineRunner commandLineRunner(IPlaneRepository planeRepo,
                                        IAirportRepository airportRepo,
                                        IFlightRepository flightRepo,
                                        IConnectedFlightRepository connectedFlightRepo) {


        //System.out.println(yyyy_MM_dd_HH_mm_ss.format(LocalDateTime.of(1999,01,01,17,30,00)));
      //  System.out.println( (LocalDateTime.of(1999,01,01,17,30,00)));
        var gson = gson();
        return args -> {
           populator.populateRepo(planeRepo, resource_path + "plane_data.json", Plane[].class,gson);
            populator.populateRepo(airportRepo, resource_path + "airport_data.json", Airport[].class,gson);

            populator.populateRepo(flightRepo, resource_path + "flight_data.json", Flight[].class,gson);
          //  populator.populateRepo(connectedFlightRepo, resource_path + "connected_flight_data.json", ConnectedFlight[].class,gson);
        };
    }


}
