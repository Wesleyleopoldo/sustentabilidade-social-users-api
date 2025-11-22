package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record UpdateEmailRequest(@NotNull(message = "O novo email não pode ser nulo!") String email) {
}