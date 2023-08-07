package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Role;
import com.expressflight.ExpressFlight.dto.RoleDTO;
import com.expressflight.ExpressFlight.repository.IRoleRepository;
import com.expressflight.ExpressFlight.requestdto.RoleRequestDTO;
import com.expressflight.ExpressFlight.serviceInterface.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    private IRoleRepository roleRepository;

    private ModelMapper modelMapper;

    public RoleService(IRoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> convertToDTO(role))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if(!role.isPresent()) {
            throw new IllegalStateException("Role with the id " + roleId + " does not exist");
        }
        return convertToDTO(role.get());
    }


    @Override
    public RoleDTO addRole(RoleRequestDTO roleRequestDto) {
        Role role = convertToEntity(roleRequestDto);
     /*   Optional<Role> existingRole = roleRepository.findByCode(role.getCode());
        if(existingRole.isPresent()) {
            throw new IllegalStateException("Role with the code " + role.getCode() + "already exists.");
        }*/
        roleRepository.save(role);
        return convertToDTO(role);
    }

    @Override
    public RoleDTO deleteRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if(!role.isPresent()) {
            throw new IllegalStateException("Role with the id " + roleId + " does not exist");
        }
        roleRepository.deleteById(roleId);
        return convertToDTO(role.get());
    }

    @Override
    @Transactional
    public RoleDTO updateRole(RoleRequestDTO roleRequestDto, Long roleId) {
        Role role = convertToEntity(roleRequestDto);
        Optional<Role> existingRole = roleRepository.findById(roleId);
        if(!existingRole.isPresent()) {
            throw new IllegalStateException("Role with the user email " + existingRole.get().getEmail() + " does not exist.");
        }
        if(role.getEmail() != null){
            existingRole.get().setEmail(role.getEmail());
        }
        if(role.getRole() != null){
            existingRole.get().setRole(role.getRole());
        }
        return convertToDTO(existingRole.get());
    }


    private RoleDTO convertToDTO(Role role) {
        RoleDTO RoleDto = modelMapper.map(role, RoleDTO.class);
        return RoleDto;
    }

    private Role convertToEntity(RoleRequestDTO roleRequestDto) {
        Role role = modelMapper.map(roleRequestDto, Role.class);
        return role;
    }

}
