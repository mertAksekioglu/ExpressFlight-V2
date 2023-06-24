package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Plane;
import com.expressflight.ExpressFlight.dto.PlaneDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PlaneDTOMapper {

    private ModelMapper modelMapper;


    public PlaneDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Plane, PlaneDTO> Converter = new Converter<Plane, PlaneDTO>()
        {
            public PlaneDTO convert(MappingContext<Plane, PlaneDTO> context)
            {
                Plane s = context.getSource();
                PlaneDTO d = context.getDestination();

                d.setModel(s.getModel());
                d.setCode(s.getCode());
                d.setYearOfProduction(s.getYearOfProduction());
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

}
