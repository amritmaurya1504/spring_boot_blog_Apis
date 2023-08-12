package com.rajamrit.SpringBoot_Blog_app.exceptions;

import com.rajamrit.SpringBoot_Blog_app.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message =ex.getMessage();
        ApiResponse res = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.NOT_FOUND);
    }

}
