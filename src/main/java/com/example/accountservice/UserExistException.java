package com.example.accountservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User exist!")
public class UserExistException extends RuntimeException {
    public UserExistException(String cause) {
        super(cause);
    }
}
