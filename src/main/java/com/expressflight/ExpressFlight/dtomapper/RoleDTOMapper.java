package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Role;
import com.expressflight.ExpressFlight.dto.RoleDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class RoleDTOMapper {

    private ModelMapper modelMapper;


    public RoleDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Role, RoleDTO> Converter = new Converter<Role, RoleDTO>()
        {
            public RoleDTO convert(MappingContext<Role, RoleDTO> context)
            {
                Role s = context.getSource();
                RoleDTO d = context.getDestination();

                d.setEmail(s.getEmail());
                d.setRole(s.getRole());
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

}