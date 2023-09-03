package com.expressflight.ExpressFlight.util;

import com.expressflight.ExpressFlight.domain.*;
import com.expressflight.ExpressFlight.repository.*;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
@Component
public class DatabasePopulator implements IDatabasePopulator {

    private final String resource_path = "src/main/resources/data/";

    private IPlaneRepository planeRepository;

    private IAirportRepository airportRepository;

    private ICoordinateRepository coordinateRepository;

    private IFlightRepository flightRepository;

    private IConnectedFlightRepository connectedFlightRepository;

    private ISeatConfigurationRepository seatConfigurationRepository;

    private IPassengerRepository passengerRepository;

    private IMemberRepository memberRepository;

    private IRoleRepository roleRepository;

    private SeatMapFactory seatMapFactory;

    private JPARepoPopulator populator;

    public DatabasePopulator(JPARepoPopulator populator, IPlaneRepository planeRepository, IAirportRepository airportRepository,
                     ICoordinateRepository coordinateRepository, IFlightRepository flightRepository,
                     IConnectedFlightRepository connectedFlightRepository, ISeatConfigurationRepository seatConfigurationRepository,
                     IPassengerRepository passengerRepository, SeatMapFactory seatMapFactory,
                     IMemberRepository memberRepository, IRoleRepository roleRepository) {

        this.populator = populator;
        this.planeRepository = planeRepository;
        this.airportRepository = airportRepository;
        this.coordinateRepository = coordinateRepository;
        this.flightRepository = flightRepository;
        this.connectedFlightRepository = connectedFlightRepository;
        this.seatConfigurationRepository = seatConfigurationRepository;
        this.passengerRepository = passengerRepository;
        this.seatMapFactory = seatMapFactory;
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void populateDatabase(Gson gson) {
        populator.populateRepo(planeRepository, resource_path + "plane_data.json", Plane[].class,gson);
        populator.populateRepo(airportRepository, resource_path + "airport_data.json", Airport[].class,gson);
        populator.populateRepo(flightRepository, resource_path + "flight_data.json", Flight[].class,gson);
        populator.populateRepo(connectedFlightRepository, resource_path + "connected_flight_data.json", ConnectedFlight[].class,gson);
        populator.populateRepo(seatConfigurationRepository, resource_path + "seat_configuration_data.json", SeatConfiguration[].class,gson);
        populator.populateRepo(passengerRepository, resource_path + "passenger_data.json", Passenger[].class,gson);
        populator.populateRepo(coordinateRepository, resource_path + "coordinate_data.json", Coordinate[].class,gson);
        populator.populateRepo(memberRepository, resource_path + "member_data.json", Member[].class,gson);
        populator.populateRepo(roleRepository, resource_path + "role_data.json", Role[].class,gson);
    }
}
