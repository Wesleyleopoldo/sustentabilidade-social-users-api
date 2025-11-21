package com.syntech.sustentabilidadesocial.sustentabilidade_social.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.responses.ErrorResponse;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.AlreadyExistsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.BadCredentialsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.EmailNotValidException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.InternalErrorException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(500).body(new ErrorResponse(ex.getMessage(), 500));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex)  {
        return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage(), 404));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(Exception ex) {
        return ResponseEntity.status(409).body(new ErrorResponse(ex.getMessage(), 409));
    }

    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotValidException(Exception ex) {
        return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), 400));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(Exception ex) {
        return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), 400));
    }
}
