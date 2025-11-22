package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record UpdateNameRequest(@NotNull(message = "O novo nome não pode ser nulo!") String name) {
}
