package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos;

import java.util.UUID;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;

    private String slug;

    private String profilePictureUrl;

    private String name;

    private String email;

    private String password;

    private RoleEnum role;
}
