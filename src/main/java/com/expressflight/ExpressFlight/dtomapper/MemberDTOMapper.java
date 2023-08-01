package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Member;
import com.expressflight.ExpressFlight.dto.MemberDTO;
import com.expressflight.ExpressFlight.service.PassengerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class MemberDTOMapper {

    private ModelMapper modelMapper;

    private PassengerService passengerService;

    public MemberDTOMapper(ModelMapper modelMapper, PassengerService passengerService) {
        this.modelMapper = modelMapper;
        this.passengerService = passengerService;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Member, MemberDTO> Converter = new Converter<Member, MemberDTO>()
        {
            public MemberDTO convert(MappingContext<Member, MemberDTO> context)
            {
                Member s = context.getSource();
                MemberDTO d = context.getDestination();

                d.setEmail(s.getEmail());
                d.setPassword(s.getPassword());
                d.setActive(s.getActive());
                d.setPassenger(passengerService.getPassenger(s.getPassenger()));
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

}
