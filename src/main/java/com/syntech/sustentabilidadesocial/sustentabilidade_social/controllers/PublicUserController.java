package com.syntech.sustentabilidadesocial.sustentabilidade_social.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.CreateUserRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.services.UserServices;

import jakarta.validation.Valid;

@RequestMapping("/public/users")
@RestController
public class PublicUserController {
    
    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody CreateUserRequest newUserData) throws Exception {
        Map<String, Object> newUser = userServices.createUser(newUserData);
        return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/user/{slug}")
    public ResponseEntity<Map<String, Object>> getUserBySlug(@PathVariable String slug) throws Exception {

        Map<String, Object> user = userServices.getUserBySlug(slug);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() throws Exception {
        List<Map<String, Object>> users = userServices.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }
}
