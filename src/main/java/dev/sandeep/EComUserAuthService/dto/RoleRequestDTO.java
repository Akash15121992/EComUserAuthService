package dev.sandeep.EComUserAuthService.dto;

import dev.sandeep.EComUserAuthService.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO extends BaseModel {
    private String roleName;
    private String description;
}
