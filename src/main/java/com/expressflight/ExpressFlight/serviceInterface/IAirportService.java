package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.AirportDTO;

import java.util.List;

public interface IAirportService {



    public List<AirportDTO> getAllAirports();

    public AirportDTO getAirport(Long airportId);

    public AirportDTO getAirportByCodeIATA(String airportCode);

    public AirportDTO addAirport(AirportDTO airportDto);

    public AirportDTO deleteAirport(Long airportId);

    public AirportDTO updateAirport(AirportDTO airportDto, Long airportId);
}
