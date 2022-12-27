package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.dto.AirportDTO;

import java.util.List;

public interface IAirportService {



    public List<AirportDTO> getAllAirports();

    public AirportDTO getAirport(Long airportId);

    public AirportDTO getAirportByCode(String airportCode);

    public AirportDTO addAirport(AirportDTO airportDto);

    public AirportDTO deleteAirport(Long airportId);

    public AirportDTO updateAirport(AirportDTO airportDto, Long airportId);
}
