package com.example.demo.global.apiPayload.exception;

import com.example.demo.global.apiPayload.exception.InvalidPageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPageException.class)
    public ResponseEntity<String> handleInvalidPage(InvalidPageException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}