package com.syntech.sustentabilidadesocial.sustentabilidade_social.errors;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
