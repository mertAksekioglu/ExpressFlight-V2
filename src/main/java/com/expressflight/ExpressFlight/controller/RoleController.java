package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.RoleDTO;
import com.expressflight.ExpressFlight.requestdto.RoleRequestDTO;
import com.expressflight.ExpressFlight.service.RoleService;
import com.expressflight.ExpressFlight.serviceInterface.IRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/admin/role")
@CrossOrigin(origins = "*")
public class RoleController {

    private IRoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping(value = "/get-id")
    public RoleDTO getRole(@RequestParam(value = "id") Long roleId) {
        return roleService.getRole(roleId);
    }

    @PostMapping(value = "/add-role")
    public RoleDTO addRole(@RequestBody RoleRequestDTO roleRequestDto) {
        return roleService.addRole(roleRequestDto);
    }

    @DeleteMapping(value = "/delete-id")
    public RoleDTO deleteRole(@RequestParam(value = "id") Long roleId) {
        return roleService.deleteRole(roleId);
    }

    @PutMapping(value = "/update-role")
    public RoleDTO updateRole(@RequestBody RoleRequestDTO roleRequestDto, @RequestParam(value = "id") Long roleId) {
        return roleService.updateRole(roleRequestDto, roleId);
    }

}
