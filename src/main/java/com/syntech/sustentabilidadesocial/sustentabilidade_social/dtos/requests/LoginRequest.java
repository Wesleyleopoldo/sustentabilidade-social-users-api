package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull(message = "O email não pode ser nulo!") String email, @NotNull(message = "A senha não pode ser nulo!") String password) {
}
