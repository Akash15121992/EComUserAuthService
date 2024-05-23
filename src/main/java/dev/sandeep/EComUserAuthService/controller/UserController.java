package dev.sandeep.EComUserAuthService.controller;

import dev.sandeep.EComUserAuthService.dto.LoginRequestDTO;
import dev.sandeep.EComUserAuthService.dto.SignUpRequestDTO;
import dev.sandeep.EComUserAuthService.dto.UserResponseDTO;
import dev.sandeep.EComUserAuthService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){

        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorisation") String authToken){

        return ResponseEntity.ok(userService.logout(authToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){

        return ResponseEntity.ok(userService.signUp(signUpRequestDTO));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestHeader("Authorisation") String authToken){

        return ResponseEntity.ok(userService.validateToken(authToken));
    }

}
