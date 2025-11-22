package com.syntech.sustentabilidadesocial.sustentabilidade_social.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.BadCredentialsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.InternalErrorException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("sustentabilidade-social") // quem gerou o token
                    .withSubject(user.getId().toString()) // identificação principal do usuário
                    .withIssuedAt(new Date()) // data de criação
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // expira em 1 hora
                    .sign(algorithm); // assinatura
        } catch (Exception ex) {
            throw new InternalErrorException("Erro inesperado ao criar o token jwt");
        }

    }

    // Função que valida token jwt...
    public String validateToken(String token) throws BadCredentialsException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // Algoritmo utilizado para criação do token jwt...

            JWTVerifier verifier = JWT.require(algorithm) //
                    .withIssuer("sustentabilidade-social")
                    .build();

            DecodedJWT decoded = verifier.verify(token);

            return decoded.getSubject();
        } catch (Exception ex) {
            throw new BadCredentialsException("Token inválido");
        }
    }
}
