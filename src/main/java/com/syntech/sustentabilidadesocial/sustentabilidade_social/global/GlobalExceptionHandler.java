package com.syntech.sustentabilidadesocial.sustentabilidade_social.global;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.responses.ErrorResponse;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.AlreadyExistsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.BadCredentialsException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.EmailNotValidException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.InternalErrorException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.InvalidTokenException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.NotFoundException;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.errors.NullPointerException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(InternalErrorException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(500).body(new ErrorResponse("Erro inesperado", 500));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex) {
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

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(Exception ex) {
        return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), 400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(400).body(ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(err -> new ErrorResponse(err.getDefaultMessage(), 400)).toList()
    );
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(Exception ex) {
        return ResponseEntity.status(403).body(new ErrorResponse(ex.getMessage(), 403));
    }
        
}
