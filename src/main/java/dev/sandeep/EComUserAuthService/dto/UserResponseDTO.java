package dev.sandeep.EComUserAuthService.dto;

import dev.sandeep.EComUserAuthService.entity.Role;
import dev.sandeep.EComUserAuthService.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private String name;
    private String email;
    private List<RoleResponseDTO> roles;

    public static UserResponseDTO from(User user){
        if(user == null){
            return null;
        }
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.name = user.getName();
        userResponseDTO.email = user.getEmailId();
        userResponseDTO.roles = new ArrayList<>();

        for (Role role : user.getRoles()){
            RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
            roleResponseDTO.setDescription(role.getDescription());
            roleResponseDTO.setRole(role.getRoleName());
            userResponseDTO.roles.add(roleResponseDTO);
        }
        return  userResponseDTO;
    }

    //get User entity from userResponseDTO
    public static User from(UserResponseDTO userResponseDTO){
        return null;
    }
}
