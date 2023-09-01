package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Passenger;
import com.expressflight.ExpressFlight.dto.PassengerDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PassengerDTOMapper {

    private ModelMapper modelMapper;

    public PassengerDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Passenger, PassengerDTO> Converter = new Converter<Passenger, PassengerDTO>()
        {
            public PassengerDTO convert(MappingContext<Passenger, PassengerDTO> context)
            {
                Passenger s = context.getSource();
                PassengerDTO d = context.getDestination();

                d.setName(s.getName());
                d.setSurname(s.getSurname());
                d.setEmail(s.getEmail());
                d.setPhone(s.getPhone());
                d.setGender(s.getGender());
                d.setAge(s.getAge());
                d.setDateOfBirth(s.getDateOfBirth());
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

}
