package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.AirportDTO;
import com.expressflight.ExpressFlight.requestdto.AirportRequestDTO;

import java.util.List;

public interface IAirportService {

    public List<AirportDTO> getAllAirports();

    public AirportDTO getAirport(Long airportId);

    public AirportDTO getAirportByCodeIATA(String airportCode);

    public AirportDTO addAirport(AirportRequestDTO airportRequestDto);

    public AirportDTO deleteAirport(Long airportId);

    public AirportDTO updateAirport(AirportRequestDTO airportRequestDto, Long airportId);

}
