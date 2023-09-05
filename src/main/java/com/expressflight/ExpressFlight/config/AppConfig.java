package com.expressflight.ExpressFlight.config;


import com.expressflight.ExpressFlight.deserializer.LocalDateDeserializer;
import com.expressflight.ExpressFlight.deserializer.LocalDateTimeDeserializer;
import com.expressflight.ExpressFlight.domain.*;
import com.expressflight.ExpressFlight.integration.IIntegration;
import com.expressflight.ExpressFlight.microservice.SunExpressFlightIntegrationService;
import com.expressflight.ExpressFlight.microservice.TicketBooker;
import com.expressflight.ExpressFlight.repository.*;
import com.expressflight.ExpressFlight.serializer.LocalDateSerializer;
import com.expressflight.ExpressFlight.serializer.LocalDateTimeSerializer;
import com.expressflight.ExpressFlight.service.SeatConfigurationService;
import com.expressflight.ExpressFlight.serviceInterface.ISeatConfigurationService;
import com.expressflight.ExpressFlight.util.IDatabasePopulator;
import com.expressflight.ExpressFlight.util.JPARepoPopulator;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Configuration
public class AppConfig {

    private final DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private IDatabasePopulator databasePopulator;

    public AppConfig(IDatabasePopulator databasePopulator) {
        this.databasePopulator = databasePopulator;
    }

    @Primary
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
    public RestTemplate restTemplate() {return new RestTemplate();}

    @Bean
    CommandLineRunner commandLineRunner(SunExpressFlightIntegrationService sunExpressFlightIntegrationService,
    ISeatConfigurationService seatConfigurationService) {
        return args -> {
            var gson = gson();
            databasePopulator.populateDatabase(gson);
            doAllFlightIntegrations(sunExpressFlightIntegrationService);
            configureAllUnconfiguredSeatConfigurations(seatConfigurationService);
        };
    }

    public void doAllFlightIntegrations(SunExpressFlightIntegrationService sunExpressFlightIntegrationService) {
        sunExpressFlightIntegrationService.integrate();
    }

    public void configureAllUnconfiguredSeatConfigurations(ISeatConfigurationService seatConfigurationService) {
        seatConfigurationService.configureAllUnconfiguredSeatConfigurations();
    }

    public void bookExampleTickets(TicketBooker ticketBooker) {
        ticketBooker.bookTicket(1L,"1A",1L);
        ticketBooker.bookTicket(1L,"1C",2L);
    }

}