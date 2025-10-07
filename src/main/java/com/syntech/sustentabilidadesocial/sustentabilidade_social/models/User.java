package com.syntech.sustentabilidadesocial.sustentabilidade_social.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Builder;

@Builder
public class User {
    
    @Id(nullable = false)

    private UUID id;
    private String slug;
    private String profilePictureUrl;
    private String name;
    private String email;
    private String password;
    private String role;
}
