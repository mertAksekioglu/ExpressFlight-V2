package com.expressflight.ExpressFlight.config;


import com.expressflight.ExpressFlight.domain.*;
import com.expressflight.ExpressFlight.microservice.SunExpressFlightIntegrationService;
import com.expressflight.ExpressFlight.repository.*;
import com.expressflight.ExpressFlight.service.FlightService;
import com.google.gson.*;
import com.expressflight.ExpressFlight.deserializer.LocalDateDeserializer;
import com.expressflight.ExpressFlight.deserializer.LocalDateTimeDeserializer;
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

    /*@Autowired
    SunExpressFlightIntegrationService sunExpressFlightIntegrationService;*/

    String resource_path = "D:\\Spring MVC Projects\\ExpressFlight\\src\\main\\resources\\";


    @Bean
    CommandLineRunner commandLineRunner(IPlaneRepository planeRepo,
                                        IAirportRepository airportRepo,
                                        IFlightRepository flightRepo,
                                        IConnectedFlightRepository connectedFlightRepo,
                                        ISeatConfigurationRepository seatConfigurationRepository,
                                        IPassengerRepository passengerRepository,
                                        Gson gson) {



        // TODO Burada Repo değil de service ile çalışan bir populator lazım


        return args -> {
           populator.populateRepo(planeRepo, resource_path + "data/plane_data.json", Plane[].class,gson);
            populator.populateRepo(airportRepo, resource_path + "data/airport_data.json", Airport[].class,gson);
            populator.populateRepo(flightRepo, resource_path + "data/flight_data.json", Flight[].class,gson);
            populator.populateRepo(connectedFlightRepo,
                    resource_path + "data/connected_flight_data.json", ConnectedFlight[].class,gson);
            populator.populateRepo(seatConfigurationRepository,
                    resource_path + "data/seat_configuration_data.json", SeatConfiguration[].class,gson);
            populator.populateRepo(passengerRepository, resource_path + "data/passenger_data.json", Passenger[].class,gson);



        };
    }


}
