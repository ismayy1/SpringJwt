package com.tpe.controller;

import com.tpe.dto.UserRegisterDTO;
import com.tpe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {

    private UserService userService;

    @PostMapping("/register")   // http://localhost:8080/register + POST + JSON Body
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.saveUser(userRegisterDTO);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "User: " + userRegisterDTO.getUserName() + " registered successfully!");

        return new ResponseEntity<>(map, HttpStatus.CREATED); //201
    }
}
