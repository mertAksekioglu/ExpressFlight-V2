package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.dto.SeatDTO;
import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.enums.SeatType;
import com.expressflight.ExpressFlight.serviceInterface.ICoordinateService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class SeatDTOMapper {

    private ModelMapper modelMapper;

    public SeatDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Seat, SeatDTO> Converter = new Converter<Seat, SeatDTO>()
        {
            public SeatDTO convert(MappingContext<Seat, SeatDTO> context)
            {
                Seat s = context.getSource();
                SeatDTO d = context.getDestination();

                d.setCode(s.getCode());
                d.setType(s.getType());
                d.setStatus(s.getStatus());
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }
    
}
