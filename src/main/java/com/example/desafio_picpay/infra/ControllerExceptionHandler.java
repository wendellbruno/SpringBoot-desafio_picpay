package com.example.desafio_picpay.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.desafio_picpay.dtos.ExceptionsDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntity(DataIntegrityViolationException exception){
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO("Usuario já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionsDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGEneralException(Exception exception){
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionsDTO);
    }
    
}
