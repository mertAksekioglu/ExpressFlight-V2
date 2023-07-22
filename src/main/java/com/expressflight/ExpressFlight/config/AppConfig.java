package com.expressflight.ExpressFlight.config;


import com.expressflight.ExpressFlight.deserializer.LocalDateDeserializer;
import com.expressflight.ExpressFlight.deserializer.LocalDateTimeDeserializer;
import com.expressflight.ExpressFlight.domain.*;
import com.expressflight.ExpressFlight.microservice.SunExpressFlightIntegrationService;
import com.expressflight.ExpressFlight.microservice.TicketBooker;
import com.expressflight.ExpressFlight.repository.*;
import com.expressflight.ExpressFlight.serializer.LocalDateSerializer;
import com.expressflight.ExpressFlight.serializer.LocalDateTimeSerializer;
import com.expressflight.ExpressFlight.util.JPARepoPopulator;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Configuration
public class AppConfig {

    private final DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern("HH:mm");

    private final String resource_path = "src/main/resources/data/";

    private IPlaneRepository planeRepository;

    private IAirportRepository airportRepository;

    private ICoordinateRepository coordinateRepository;

    private IFlightRepository flightRepository;

    private IConnectedFlightRepository connectedFlightRepository;

    private ISeatConfigurationRepository seatConfigurationRepository;

    private IPassengerRepository passengerRepository;

    private SeatMapFactory seatMapFactory;

    private JPARepoPopulator populator;

    public AppConfig(JPARepoPopulator populator, IPlaneRepository planeRepository, IAirportRepository airportRepository,
                     ICoordinateRepository coordinateRepository, IFlightRepository flightRepository,
                     IConnectedFlightRepository connectedFlightRepository, ISeatConfigurationRepository seatConfigurationRepository,
                     IPassengerRepository passengerRepository, SeatMapFactory seatMapFactory) {

        this.populator = populator;
        this.planeRepository = planeRepository;
        this.airportRepository = airportRepository;
        this.coordinateRepository = coordinateRepository;
        this.flightRepository = flightRepository;
        this.connectedFlightRepository = connectedFlightRepository;
        this.seatConfigurationRepository = seatConfigurationRepository;
        this.passengerRepository = passengerRepository;
        this.seatMapFactory = seatMapFactory;
    }

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

    @Bean
    CommandLineRunner commandLineRunner(IPlaneRepository planeRepo,
                                        IAirportRepository airportRepo,
                                        IFlightRepository flightRepo,
                                        IConnectedFlightRepository connectedFlightRepo,
                                        ISeatConfigurationRepository seatConfigurationRepo,
                                        IPassengerRepository passengerRepo,
                                        SunExpressFlightIntegrationService sunExpressFlightIntegrationService,
                                        TicketBooker ticketBooker,
                                        Gson gson) {

        return args -> {
            populateAllRepositories(gson);
        };
    }

    public List<SeatConfiguration> configureAllFlightSeatConfigs() {
        List<SeatConfiguration> unconfiguredSeatConfigs = seatConfigurationRepository.findByIsConfigured(false);
        for (SeatConfiguration seatConfig : unconfiguredSeatConfigs)
        {
            seatConfig.setSeatMap(seatMapFactory.createSeatMap(seatConfig.getConfigName()).mapSeats());
            seatConfig.setIsConfigured(true);
        }
        return unconfiguredSeatConfigs;
    }

    public void populateAllRepositories(Gson gson) {
        populator.populateRepo(planeRepository, resource_path + "plane_data.json", Plane[].class,gson);
        populator.populateRepo(airportRepository, resource_path + "airport_data.json", Airport[].class,gson);
        populator.populateRepo(flightRepository, resource_path + "flight_data.json", Flight[].class,gson);
        populator.populateRepo(connectedFlightRepository, resource_path + "connected_flight_data.json", ConnectedFlight[].class,gson);
        populator.populateRepo(seatConfigurationRepository, resource_path + "seat_configuration_data.json", SeatConfiguration[].class,gson);
        populator.populateRepo(passengerRepository, resource_path + "passenger_data.json", Passenger[].class,gson);
        populator.populateRepo(coordinateRepository, resource_path + "coordinate_data.json", Coordinate[].class,gson);
    }

    public void doAllFlightIntegrations(SunExpressFlightIntegrationService sunExpressFlightIntegrationService) {
        sunExpressFlightIntegrationService.integrate();
    }

    public void bookExampleTickets(TicketBooker ticketBooker) {
        ticketBooker.bookTicket(1L,"1A",1L);
        ticketBooker.bookTicket(1L,"1C",2L);
    }

}