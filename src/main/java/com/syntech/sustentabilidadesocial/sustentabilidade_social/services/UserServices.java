package com.syntech.sustentabilidadesocial.sustentabilidade_social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.repository.UserRepository;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository userRepository;

    public UserServices() {
    }

}
