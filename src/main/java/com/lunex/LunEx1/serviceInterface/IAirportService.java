package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.dto.AirportDTO;

import java.util.List;

public interface IAirportService {



    public List<AirportDTO> getAllAirports();

    public AirportDTO getAirport(Long airportId);

    public void addAirport(AirportDTO airportDto);

    public void deleteAirport(Long airportId);

    public void updateAirport(AirportDTO airportDto);
}
