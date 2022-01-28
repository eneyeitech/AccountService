package com.example.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;


@RestController
public class AuthenticationController {

    private Store store;

    @Autowired
    public AuthenticationController(Store store) {
        this.store = store;
    }

    @PostMapping("api/auth/signup")
    //@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad Request")
    public Map<String, String> signUp(@RequestBody User user) {

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getEmail().isBlank() || user.getEmail().equals("") || user.getEmail().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!user.getEmail().matches("\\w+(@acme.com)$")){
            //return new ResponseEntity<>(Map.of("error", "email not valid"), HttpStatus.BAD_REQUEST);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getLastname().isBlank() || user.getLastname().equals("") || user.getLastname().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().isBlank() || user.getPassword().equals("") || user.getPassword().isEmpty() ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getName().isBlank() || user.getName().equals("") || user.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //return new ResponseEntity<>(user, HttpStatus.OK);
        store.getUserStore().put(user.getEmail(), user);
        return Map.of("name", user.getName(), "lastname", user.getLastname(), "email", user.getEmail());
    }

    @PostMapping("api/auth/changepass")
    public String changePassword() {
        return "changePassword";
    }
}
