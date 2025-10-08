package com.syntech.sustentabilidadesocial.sustentabilidade_social.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.UserDTO;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.helper.DTOUtils;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.repository.UserRepository;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.requests.CreateUserRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.UserUtils;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository userRepository;

    public UserServices() {
    }

    public Map<String, Object> createUser(CreateUserRequest newUserData) throws Exception {
        
        String slug;
        Optional<User> findUser = userRepository.findByEmail(newUserData.email());

        if (!UserUtils.isDomainValid(newUserData.email())) {
            throw new Exception("Esse email não é válido...");
        }

        if (findUser.isPresent()) {
            throw new Exception("Esse email já está cadastrado...");
        }

        do {
            slug = UserUtils.generateSlug(newUserData.name());
            findUser = userRepository.findBySlug(slug);

            if (findUser.isEmpty()) break;
        } while (true);

        User user = User.builder().
        profilePictureUrl(!newUserData.pictureProfile().isEmpty() ? newUserData.pictureProfile() : null)
        .slug(slug)
        .name(newUserData.name())
        .email(newUserData.email())
        .password(newUserData.password())
        .build();

        User newUser = userRepository.save(user);

        UserDTO userDTO = UserDTO.builder()
        .profilePictureUrl(newUser.getProfilePictureUrl())
        .name(newUser.getName())
        .build();

        Map<String, Object> cleanDTO = DTOUtils.cleanDTO(userDTO);

        return cleanDTO;
    }

}
