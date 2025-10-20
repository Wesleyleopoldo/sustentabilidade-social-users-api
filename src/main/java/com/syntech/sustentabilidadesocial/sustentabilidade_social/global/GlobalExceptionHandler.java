package com.syntech.sustentabilidadesocial.sustentabilidade_social.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.Errors.AlreadyExistsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.Errors.EmailNotValidException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.Errors.InternalErrorException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.Errors.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex)  {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(Exception ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<String> handleEmailNotValidException(Exception ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
