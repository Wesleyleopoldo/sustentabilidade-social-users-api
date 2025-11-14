package com.syntech.sustentabilidadesocial.sustentabilidade_social.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.Errors.BadCredentialsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.NotFoundException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.JWTUtils;
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


            // Gera o token
            return JWTUtils.generateToken(user);

        } catch(InternalErrorException exception) {
            throw new InternalErrorException("Erro ao criar token jwt");
        }
    }
}
