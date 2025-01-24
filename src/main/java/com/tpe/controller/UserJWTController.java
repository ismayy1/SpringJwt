package com.tpe.controller;

import com.tpe.dto.UserLoginDTO;
import com.tpe.dto.UserRegisterDTO;
import com.tpe.security.JwtUtils;
import com.tpe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @PostMapping("/register")   // http://localhost:8080/register + POST + JSON Body
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.saveUser(userRegisterDTO);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "User: " + userRegisterDTO.getUserName() + " registered successfully!");

        return new ResponseEntity<>(map, HttpStatus.CREATED); //201
    }

    @PostMapping("/login")  // http://localhost:8080/login + POST + JSON Body
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUserName(), userLoginDTO.getPassword()));
        String jwt = jwtUtils.generateToken(authentication);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED); //201
    }

    //HW: Get a user from the database
    //  1- Create a new DTO for it.
    //  2- Authorize on method level.
    //  3- Send the request with JWT in it.
}
