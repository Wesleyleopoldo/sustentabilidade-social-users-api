package com.syntech.sustentabilidadesocial.sustentabilidade_social.controllers;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.LoginRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.responses.JWTResponse;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.services.LoginServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private LoginServices loginServices;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginRequest data, HttpServletResponse response) throws Exception{
        String token = loginServices.createJWT(data.email(), data.password());
        return ResponseEntity.status(200).body(new JWTResponse(token));
    }
}