package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Classe que retorna um objeto para formar um json...
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
}