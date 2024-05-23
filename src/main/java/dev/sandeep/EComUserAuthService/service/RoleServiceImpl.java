package dev.sandeep.EComUserAuthService.service;

import dev.sandeep.EComUserAuthService.dto.RoleRequestDTO;
import dev.sandeep.EComUserAuthService.dto.RoleResponseDTO;
import dev.sandeep.EComUserAuthService.entity.Role;
import dev.sandeep.EComUserAuthService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setRoleName(roleRequestDTO.getRoleName());
        role.setDescription(roleRequestDTO.getDescription());

        return RoleResponseDTO.from(roleRepository.save(role));
    }
}
