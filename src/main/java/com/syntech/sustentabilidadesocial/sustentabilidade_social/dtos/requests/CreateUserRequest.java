package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(String pictureProfile, @NotNull(message = "O nome não pode ser nulo!") String name, @NotNull(message = "O email não pode ser nulo!") String email, @NotNull(message = "A senha não pode ser nula!") String password) {
}