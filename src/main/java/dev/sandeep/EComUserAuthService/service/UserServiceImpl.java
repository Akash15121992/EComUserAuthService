package dev.sandeep.EComUserAuthService.service;

import dev.sandeep.EComUserAuthService.dto.LoginRequestDTO;
import dev.sandeep.EComUserAuthService.dto.SignUpRequestDTO;
import dev.sandeep.EComUserAuthService.dto.UserResponseDTO;
import dev.sandeep.EComUserAuthService.entity.Role;
import dev.sandeep.EComUserAuthService.entity.User;
import dev.sandeep.EComUserAuthService.exception.InvalidCredentialException;
import dev.sandeep.EComUserAuthService.exception.RoleNotFoundException;
import dev.sandeep.EComUserAuthService.exception.UserNotFoundException;
import dev.sandeep.EComUserAuthService.repository.RoleRepository;
import dev.sandeep.EComUserAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) throws RoleNotFoundException{
        Role role = roleRepository.findById(signUpRequestDTO.getRoleId()).orElseThrow(
                () -> new RoleNotFoundException("Role not found for the given role id")
        );

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(signUpRequestDTO.getName());
        user.setEmailId(signUpRequestDTO.getEmail());
        //user.setPassword(signUpRequestDTO.getPassword());
        user.setPassword(encoder.encode(signUpRequestDTO.getPassword()));
        user.setRoles(List.of(role));

        return UserResponseDTO.from(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        //first check if the user exists or not with details of login request dto
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User savedUser = userRepository.findByEmailId(loginRequestDTO.getEmailId()).orElseThrow(
                () -> new UserNotFoundException("User Not Found ")
        );

        if(bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), savedUser.getPassword())){
            //generating token
          String userData = savedUser.getEmailId() + savedUser.getPassword() + LocalDateTime.now();
          String token = bCryptPasswordEncoder.encode(userData);
          savedUser.setToken(token);
        }else{
            throw new InvalidCredentialException();
        }
        savedUser = userRepository.save(savedUser);
        return UserResponseDTO.from(savedUser);
    }

    @Override
    public boolean validateToken(String token) {
        //if token exists in database then return true i.e validated else return false
        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        return true;
    }

    @Override
    public boolean logout(String token) {

        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        savedUser.setToken(null);
        userRepository.save(savedUser);
        return true;
    }
}
