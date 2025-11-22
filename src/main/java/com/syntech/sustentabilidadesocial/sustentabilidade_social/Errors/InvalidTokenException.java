package com.syntech.sustentabilidadesocial.sustentabilidade_social.errors;

public class InvalidTokenException extends RuntimeException {
    
    public InvalidTokenException(String errorMessage) {
        super(errorMessage);
    }
}
