package com.example.gradproject.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //controller'ın exception controlü için genel bir sınıf servisten gelen hataya istinaden bir mesaj dönmek için
public class GeneralExceptionAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerCRUDException.class)
    public ResponseEntity<?> handle(CustomerCRUDException exception) {
        return handleNotCRUD(exception.getMessage());
    }

    @ExceptionHandler(CreditApplicationCRUDException.class)
    public ResponseEntity<?> handle(CreditApplicationCRUDException exception) {
        return handleNotCRUD(exception.getMessage());
    }

    private ResponseEntity<?> handleNotCRUD(String s) {
        return new ResponseEntity<>(s, HttpStatus.EXPECTATION_FAILED);
    }
}
