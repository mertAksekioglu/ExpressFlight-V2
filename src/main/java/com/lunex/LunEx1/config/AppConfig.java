package com.lunex.LunEx1.config;

import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.repository.IAirportRepository;
import com.lunex.LunEx1.repository.IFlightRepository;
import com.lunex.LunEx1.repository.IPlaneRepository;
import com.lunex.LunEx1.util.JPARepoPopulator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;



@Configuration
@Service
public class AppConfig {

    @Autowired
    public JPARepoPopulator populator = new JPARepoPopulator();

    String resource_path = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\";



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    CommandLineRunner commandLineRunner(IPlaneRepository planeRepo, IAirportRepository airportRepo, IFlightRepository flightRepo) {
        return args -> {
           populator.populateRepo(planeRepo, resource_path + "plane_data.json", Plane[].class);
            populator.populateRepo(airportRepo, resource_path + "airport_data.json", Airport[].class);
            populator.populateRepo(flightRepo, resource_path + "flight_data.json", Flight[].class);
        };
    }


}
