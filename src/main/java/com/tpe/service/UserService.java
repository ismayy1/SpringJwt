package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.UserRole;
import com.tpe.dto.UserRegisterDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.RoleRepository;
import com.tpe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByUserName(userRegisterDTO.getUserName())){
            throw new ConflictException("A user with this username already exists: " + userRegisterDTO.getUserName());
        }

        Role role = roleRepository.findByName(UserRole.ROLE_STUDENT)
                .orElseThrow(()-> new ResourceNotFoundException("Role not found!"));

        User user = new User();

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setUserName(userRegisterDTO.getUserName());
        //user.setPassword(userRegisterDTO.getPassword());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);
    }
}
