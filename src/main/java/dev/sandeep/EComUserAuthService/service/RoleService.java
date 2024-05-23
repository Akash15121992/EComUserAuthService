package dev.sandeep.EComUserAuthService.service;

import dev.sandeep.EComUserAuthService.dto.RoleRequestDTO;
import dev.sandeep.EComUserAuthService.dto.RoleResponseDTO;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
}
