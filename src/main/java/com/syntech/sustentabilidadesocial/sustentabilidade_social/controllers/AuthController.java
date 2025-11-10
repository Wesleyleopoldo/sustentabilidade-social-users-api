package com.syntech.sustentabilidadesocial.sustentabilidade_social.controllers;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.LoginRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.responses.LoginMessages;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.services.LoginServices;
import jakarta.servlet.http.Cookie;
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
    public ResponseEntity<LoginMessages> login(@RequestBody LoginRequest data, HttpServletResponse response) throws Exception{
        String token = loginServices.createJWT(data.email(), data.password());

        // Cria o cookie
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true); // impede acesso via JavaScript
        cookie.setSecure(true);   // use true se estiver servindo via HTTPS
        cookie.setPath("/");      // cookie válido em toda a aplicação
        cookie.setMaxAge(3600);   // expira em 1 hora (em segundos)

        // Adiciona o cookie à resposta
        response.addCookie(cookie);

        return ResponseEntity.status(200).body(new LoginMessages("Login realizado com sucesso!"));
    }
}
