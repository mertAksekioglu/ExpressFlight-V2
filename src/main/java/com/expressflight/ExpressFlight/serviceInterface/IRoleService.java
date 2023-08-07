package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.RoleDTO;
import com.expressflight.ExpressFlight.requestdto.RoleRequestDTO;

import java.util.List;

public interface IRoleService {

    public List<RoleDTO> getAllRoles();

    public RoleDTO getRole(Long roleId);

    public RoleDTO addRole(RoleRequestDTO roleRequestDto);

    public RoleDTO deleteRole(Long roleId);

    public RoleDTO updateRole(RoleRequestDTO roleRequestDto, Long roleId);
    
    
}
