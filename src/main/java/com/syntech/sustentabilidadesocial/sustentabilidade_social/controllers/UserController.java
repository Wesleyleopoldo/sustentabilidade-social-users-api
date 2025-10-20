package com.syntech.sustentabilidadesocial.sustentabilidade_social.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.CreateUserRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.UpdateEmailRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.UpdateNameRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.UpdatePasswordRequest;
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
    public ResponseEntity<Map<String, Object>> updateName(@RequestParam String id, @RequestBody UpdateNameRequest newNameData) throws Exception {
        Map<String, Object> newName = userServices.updateName(id, newNameData);
        return ResponseEntity.status(200).body(newName);
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<Map<String, Object>> updateEmail(@RequestParam String id, @RequestBody UpdateEmailRequest newEmailData) throws Exception {
        Map<String, Object> newEmail = userServices.updateEmail(id, newEmailData);

        return ResponseEntity.status(200).body(newEmail);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestParam String id, @RequestBody UpdatePasswordRequest newPasswordData) throws Exception {
        Map<String, Object> userUpdated = userServices.updatePassword(id, newPasswordData);
        return ResponseEntity.status(200).body(userUpdated);
    }

    @DeleteMapping("/destroyUser/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestParam String id) throws Exception{
        Map<String, Object> deletedUser = userServices.deleteUser(id);
        return ResponseEntity.status(200).body(deletedUser);
    }
}
