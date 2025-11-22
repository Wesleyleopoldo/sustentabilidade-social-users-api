package com.syntech.sustentabilidadesocial.sustentabilidade_social.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdateEmailRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdateNameRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdatePasswordRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.helper.RequestUser;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.services.UserServices;

import jakarta.validation.Valid;

@RequestMapping("/users")
@RestController
public class UserController {
    
    @Autowired
    private UserServices userServices;

    @Autowired
    private RequestUser requestUser;

    @PutMapping("/name")
    public ResponseEntity<Map<String, Object>> updateName(@Valid @RequestBody UpdateNameRequest newNameData) throws Exception {
        String id = requestUser.getUserId();
        Map<String, Object> newName = userServices.updateName(id, newNameData);
        return ResponseEntity.status(200).body(newName);
    }

    @PutMapping("/email")
    public ResponseEntity<Map<String, Object>> updateEmail(@Valid @RequestBody UpdateEmailRequest newEmailData) throws Exception {
        String id = requestUser.getUserId();

        Map<String, Object> newEmail = userServices.updateEmail(id, newEmailData);
        return ResponseEntity.status(200).body(newEmail);
    }

    @PutMapping("/password")
    public ResponseEntity<Map<String, Object>> updatePassword(@Valid @RequestBody UpdatePasswordRequest newPasswordData) throws Exception {
        String id = requestUser.getUserId();

        Map<String, Object> userUpdated = userServices.updatePassword(id, newPasswordData);
        return ResponseEntity.status(200).body(userUpdated);
    }

    @DeleteMapping("/destroyUser")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id) throws Exception{
        Map<String, Object> deletedUser = userServices.deleteUser(id);
        return ResponseEntity.status(200).body(deletedUser);
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getUserById() throws Exception {
        
        String id = requestUser.getUserId();
        
        Map<String, Object> user = userServices.getUserById(id);
        return ResponseEntity.status(200).body(user);
    }

}
