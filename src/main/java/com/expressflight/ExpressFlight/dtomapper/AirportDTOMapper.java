package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Airport;
import com.expressflight.ExpressFlight.dto.AirportDTO;
import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import com.expressflight.ExpressFlight.serviceInterface.ICoordinateService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class AirportDTOMapper {

    private ModelMapper modelMapper;

    private ICoordinateService coordinateService;

    public AirportDTOMapper(ModelMapper modelMapper, ICoordinateService coordinateService) {
        this.modelMapper = modelMapper;
        this.coordinateService = coordinateService;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Airport, AirportDTO> Converter = new Converter<Airport, AirportDTO>()
        {
            public AirportDTO convert(MappingContext<Airport, AirportDTO> context)
            {
                Airport s = context.getSource();
                AirportDTO d = context.getDestination();

                d.setName(s.getName());
                d.setCodeIATA(s.getCodeIATA());
                d.setCodeICAO(s.getCodeICAO());
                CoordinateDTO location = coordinateService.getCoordinate(s.getLocation());
                d.setLocation(location);
                d.setTerminalCount(s.getTerminalCount());
                d.setRunwayCount(s.getRunwayCount());
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

}
