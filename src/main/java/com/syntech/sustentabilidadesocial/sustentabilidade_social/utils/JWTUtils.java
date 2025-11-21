package com.syntech.sustentabilidadesocial.sustentabilidade_social.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTUtils {

    @Value("${jwt.secret}")
    private static String secret;

    public static String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("sustentabilidade-social") // quem gerou o token
                .withSubject(user.getId().toString()) // identificação principal do usuário
                .withIssuedAt(new Date()) // data de criação
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // expira em 1 hora
                .sign(algorithm); // assinatura
    }

    // public static String validateToken(String token) {

    // }
}
