package com.example.accountservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) {

        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
