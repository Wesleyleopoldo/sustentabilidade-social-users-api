package com.syntech.sustentabilidadesocial.sustentabilidade_social.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.CreateUserRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdateEmailRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdateNameRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdatePasswordRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.services.UserServices;

@RequestMapping("/users")
@RestController
public class UserController {
    
    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody CreateUserRequest newUserData) throws Exception{
        Map<String, Object> newUser = userServices.createUser(newUserData);
        return ResponseEntity.status(201).body(newUser);
    }

    @PutMapping("/name/{id}")
    public ResponseEntity<Map<String, Object>> updateName(@PathVariable String id, @RequestBody UpdateNameRequest newNameData) throws Exception {
        Map<String, Object> newName = userServices.updateName(id, newNameData);
        return ResponseEntity.status(200).body(newName);
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<Map<String, Object>> updateEmail(@PathVariable String id, @RequestBody UpdateEmailRequest newEmailData) throws Exception {
        Map<String, Object> newEmail = userServices.updateEmail(id, newEmailData);

        return ResponseEntity.status(200).body(newEmail);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<Map<String, Object>> updatePassword(@PathVariable String id, @RequestBody UpdatePasswordRequest newPasswordData) throws Exception {
        Map<String, Object> userUpdated = userServices.updatePassword(id, newPasswordData);
        return ResponseEntity.status(200).body(userUpdated);
    }

    @DeleteMapping("/destroyUser/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id) throws Exception{
        Map<String, Object> deletedUser = userServices.deleteUser(id);
        return ResponseEntity.status(200).body(deletedUser);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String id) throws Exception {
        Map<String, Object> user = userServices.getUserById(id);
        return ResponseEntity.status(200).body(user);
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
