package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Airport;

import java.util.List;

public interface IAirportService {



    public List<Airport> getAllAirports();

    public Airport getAirport(Long airportId);

    public void addAirport(Airport airport);

    public void deleteAirport(Long airportId);

    public void updateAirport(Airport airport);
}
