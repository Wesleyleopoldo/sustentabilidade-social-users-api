package com.syntech.sustentabilidadesocial.sustentabilidade_social.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.UserDTO;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.helper.DTOUtils;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.CreateUserRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdateEmailRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdateNameRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests.UpdatePasswordRequest;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.AlreadyExistsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.EmailNotValidException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.NotFoundException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.repository.UserRepository;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.RoleEnum;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.UserUtils;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository userRepository;

    public UserServices() {
    }


    // Cria usuário...
    public Map<String, Object> createUser(CreateUserRequest newUserData) throws Exception {
        
        String slug;
        Optional<User> findUser = userRepository.findByEmail(newUserData.email());

        if (!UserUtils.isDomainValid(newUserData.email())) {
            throw new EmailNotValidException("Esse email não é válido...");
        }

        if (findUser.isPresent()) {
            throw new AlreadyExistsException("Esse email já está cadastrado...");
        }

        do {
            slug = UserUtils.generateSlug(newUserData.name());
            findUser = userRepository.findBySlug(slug);
        } while (findUser.isPresent());

        User user = User.builder().
        profilePictureUrl(!newUserData.pictureProfile().isEmpty() ? newUserData.pictureProfile() : null)
        .slug(slug)
        .name(newUserData.name())
        .email(newUserData.email())
        .password(newUserData.password())
        .role(RoleEnum.USER)
        .build();

        User newUser = userRepository.save(user);

        UserDTO userDTO = UserDTO.builder()
        .id(newUser.getId())
        .profilePictureUrl(newUser.getProfilePictureUrl())
        .name(newUser.getName())
        .build();

        return DTOUtils.cleanDTO(userDTO);
    }

    // Atualiza nome do usuário pelo id...
    public Map<String, Object> updateName(String userId, UpdateNameRequest updateNameData) throws Exception {
        
        UUID userUUID = UUID.fromString(userId);

        Optional<User> findUser = userRepository.findById(userUUID);

        if (findUser.isEmpty()) {
            throw new NotFoundException("Usuário não existe");
        }

        User user = findUser.get();

        user.setName(updateNameData.name());

        User updatedUser = userRepository.save(user);

        UserDTO userDTO = UserDTO.builder()
        .name(updatedUser.getName())
        .build();

        return DTOUtils.cleanDTO(userDTO);
    }


    // Atualiza email do usuário pelo id...
    public Map<String, Object> updateEmail(String userId, UpdateEmailRequest updateEmailData) throws Exception {

        UUID userUUID = UUID.fromString(userId);

        Optional<User> findUser = userRepository.findById(userUUID);

        Optional<User> findUserByEmail = userRepository.findByEmail(updateEmailData.email());

        if (findUser.isEmpty()) {
            throw new NotFoundException("Usuário não existe");
        }

        if (!UserUtils.isDomainValid(updateEmailData.email())) {
            throw new EmailNotValidException("Esse email não é válido...");
        }

        if (findUserByEmail.isPresent()) {
            throw new AlreadyExistsException("Esse email já está cadastrado...");
        }

        User user = findUser.get();

        user.setEmail(updateEmailData.email());

        User updatedUser = userRepository.save(user);

        UserDTO userDTO = UserDTO.builder()
        .email(updatedUser.getEmail())
        .build();

        return DTOUtils.cleanDTO(userDTO);
    }


    // Atualiza senha de usuário...
    public Map<String, Object> updatePassword(String userId, UpdatePasswordRequest updatePasswordData) throws Exception {

        UUID userUUID = UUID.fromString(userId);

        Optional<User> findUser = userRepository.findById(userUUID);

        if (findUser.isEmpty()) {
            throw new NotFoundException("Usuário não existe");
        }

        User user = findUser.get();

        user.setPassword(updatePasswordData.password());

        User updatedUser = userRepository.save(user);

        UserDTO userDTO = UserDTO.builder()
                .name(updatedUser.getName())
                .build();

        return DTOUtils.cleanDTO(userDTO);
    }


    // Deleta usuário pelo id...
    public Map<String, Object> deleteUser(String userId) throws Exception {

        UUID userUUID = UUID.fromString(userId);

        Optional<User> findUser = userRepository.findById(userUUID);

        if (findUser.isEmpty()) {
            throw new NotFoundException("Usuário não existe");
        }

        User user = findUser.get();

        userRepository.delete(user);

        UserDTO userDTO = UserDTO.builder()
                .name(user.getName())
                .build();

        return DTOUtils.cleanDTO(userDTO);
    }


    // Buscar usuário pelo id...
    public Map<String, Object> getUserById(String userId) throws Exception {

        UUID userUUID = UUID.fromString(userId);

        Optional<User> findUser = userRepository.findById(userUUID);

        if (findUser.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        User user = findUser.get();

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .slug(user.getSlug())
                .profilePictureUrl(user.getProfilePictureUrl())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return DTOUtils.cleanDTO(userDTO);
    }


    // Buscar usuário pelo slug...
    public Map<String, Object> getUserBySlug(String slug) throws Exception {

        Optional<User> findUser = userRepository.findBySlug(slug);

        if (findUser.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        User user = findUser.get();

        UserDTO userDTO = UserDTO.builder()
                .slug(user.getSlug())
                .profilePictureUrl(user.getProfilePictureUrl())
                .name(user.getName())
                .build();

        return DTOUtils.cleanDTO(userDTO);
    }


    // Listar usuários...
    public List<Map<String, Object>> getAllUsers() throws Exception {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new NotFoundException("Não existe usuários cadastrados");
        }

        List<UserDTO> usersDTO = users.stream().map(user -> UserDTO.builder()
                .slug(user.getSlug())
                .profilePictureUrl(user.getProfilePictureUrl())
                .name(user.getName())
                .build()
        ).toList();

        List<Map<String, Object>> cleanDTOSList;

        cleanDTOSList = usersDTO.stream().map(userDTO -> {
            try {
                return DTOUtils.cleanDTO(userDTO);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        return cleanDTOSList;
    }

}
