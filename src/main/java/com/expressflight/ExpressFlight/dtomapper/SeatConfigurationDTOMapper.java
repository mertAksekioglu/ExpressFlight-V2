package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;
import com.expressflight.ExpressFlight.dto.SeatDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class SeatConfigurationDTOMapper {

    private ModelMapper modelMapper;

    public SeatConfigurationDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<SeatConfiguration, SeatConfigurationDTO> Converter = new Converter<SeatConfiguration, SeatConfigurationDTO>()
        {
            public SeatConfigurationDTO convert(MappingContext<SeatConfiguration, SeatConfigurationDTO> context)
            {
                SeatConfiguration s = context.getSource();
                SeatConfigurationDTO d = context.getDestination();

                d.setConfigPlane(s.getConfigPlane());
                d.setConfigName(s.getConfigName());
                d.setIsConfigured(s.getIsConfigured());
                if(s.getIsConfigured()) {
                    SeatDTO[][] seatMap = new SeatDTO[s.getSeatMap().length][s.getSeatMap()[0].length];
                    for (int i = 0; i < seatMap.length; i++) {
                        for (int j = 0; j < seatMap[0].length; j++) {
                            seatMap[i][j] = convertToDTO(s.getSeatMap()[i][j]);
                        }
                    }
                    d.setSeatMap(seatMap);
                }

                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

    private SeatDTO convertToDTO(Seat seat) {
        SeatDTO SeatDto = modelMapper.map(seat, SeatDTO.class);
        return SeatDto;
    }
    
}
