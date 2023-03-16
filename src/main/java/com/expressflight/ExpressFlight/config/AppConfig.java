package com.expressflight.ExpressFlight.config;


import com.google.gson.*;
import com.expressflight.ExpressFlight.deserializer.LocalDateDeserializer;
import com.expressflight.ExpressFlight.deserializer.LocalDateTimeDeserializer;
import com.expressflight.ExpressFlight.domain.Airport;
import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.domain.Plane;
import com.expressflight.ExpressFlight.repository.IAirportRepository;
import com.expressflight.ExpressFlight.repository.IConnectedFlightRepository;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.repository.IPlaneRepository;
import com.expressflight.ExpressFlight.serializer.LocalDateSerializer;
import com.expressflight.ExpressFlight.serializer.LocalDateTimeSerializer;
import com.expressflight.ExpressFlight.util.JPARepoPopulator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Service
@Configuration
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

    @Bean
    public RestTemplate restTemplate() {return new RestTemplate();}



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
           populator.populateRepo(planeRepo, resource_path + "data/plane_data.json", Plane[].class,gson);
            populator.populateRepo(airportRepo, resource_path + "data/airport_data.json", Airport[].class,gson);

            populator.populateRepo(flightRepo, resource_path + "data/flight_data.json", Flight[].class,gson);
          //  populator.populateRepo(connectedFlightRepo, resource_path + "connected_flight_data.json", ConnectedFlight[].class,gson);

        };
    }


}
