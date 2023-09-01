package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Coordinate;
import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class CoordinateDTOMapper {

    private ModelMapper modelMapper;

    public CoordinateDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Coordinate, CoordinateDTO> Converter = new Converter<Coordinate, CoordinateDTO>()
        {
            public CoordinateDTO convert(MappingContext<Coordinate, CoordinateDTO> context)
            {
                Coordinate s = context.getSource();
                CoordinateDTO d = context.getDestination();

                d.setLongitude(s.getLongitude());
                d.setLatitude(s.getLatitude());
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

}
