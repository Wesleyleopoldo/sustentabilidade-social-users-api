package com.syntech.sustentabilidadesocial.sustentabilidade_social.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.Errors.BadCredentialsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.NotFoundException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.InternalErrorException;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginServices {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    public String createJWT(String email, String password) throws Exception {
        Optional<User> findUser = userRepository.findByEmail(email);

        if (findUser.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado...");
        }

        User user = findUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Gera o token
            return JWT.create()
                    .withIssuer("sustentabilidade-social") // quem gerou o token
                    .withSubject(user.getId().toString()) // identificação principal do usuário
                    .withIssuedAt(new Date()) // data de criação
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // expira em 1 hora
                    .sign(algorithm); // assinatura

        } catch(InternalErrorException exception) {
            throw new InternalErrorException("Erro ao criar token jwt");
        }
    }
}
