package dev.sandeep.EComUserAuthService.service;

import dev.sandeep.EComUserAuthService.dto.LoginRequestDTO;
import dev.sandeep.EComUserAuthService.dto.SignUpRequestDTO;
import dev.sandeep.EComUserAuthService.dto.UserResponseDTO;
import dev.sandeep.EComUserAuthService.exception.RoleNotFoundException;

public interface UserService {
    UserResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) throws RoleNotFoundException;
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
    boolean logout(String token);
}
