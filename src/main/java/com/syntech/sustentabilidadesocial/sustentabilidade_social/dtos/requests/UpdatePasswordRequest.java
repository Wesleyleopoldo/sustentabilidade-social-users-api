package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordRequest(@NotNull(message = "A nova senha não pode ser nula!") String password) {
}
