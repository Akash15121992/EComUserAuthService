package dev.sandeep.EComUserAuthService.dto;

import dev.sandeep.EComUserAuthService.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleResponseDTO {
    private String role;
    private String description;
    private UUID roleId;

    public static RoleResponseDTO from(Role role){
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();

        roleResponseDTO.role = role.getRoleName();
        roleResponseDTO.description = role.getDescription();
        roleResponseDTO.roleId = role.getId();
        return  roleResponseDTO;
    }
}
