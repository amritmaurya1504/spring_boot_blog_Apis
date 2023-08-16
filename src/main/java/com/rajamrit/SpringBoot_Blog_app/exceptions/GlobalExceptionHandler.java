package com.rajamrit.SpringBoot_Blog_app.exceptions;

import com.rajamrit.SpringBoot_Blog_app.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message =ex.getMessage();
        ApiResponse res = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> res = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((objectError ->{
            String fieldName = ((FieldError)objectError).getField();
            String message = objectError.getDefaultMessage();
            res.put(fieldName, message);
        } ));

        return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
    }
}
