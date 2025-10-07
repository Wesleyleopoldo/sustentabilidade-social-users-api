package com.syntech.sustentabilidadesocial.sustentabilidade_social.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_slug", nullable = false, unique = true)
    private String slug;

    @Column(nullable = true)
    private String profilePictureUrl;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.USER;
}