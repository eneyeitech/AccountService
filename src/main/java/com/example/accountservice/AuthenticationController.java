package com.example.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class AuthenticationController {

    private Store store;
    private long id = 0L;

    @Autowired
    UserRepository userRepo;

    @Autowired
    BCryptEncoderConfig b;

    @Autowired
    public AuthenticationController(Store store) {
        this.store = store;
    }

    @PostMapping("api/auth/signup")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) {
        if (user.getEmail().isBlank() || user.getEmail().equals("") || user.getEmail().isEmpty()){
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Map.of("error", "email empty"), HttpStatus.BAD_REQUEST);
        }
        if (!user.getEmail().matches("\\w+(@acme.com)$")){
            return new ResponseEntity<>(Map.of("error", "email incorrect"), HttpStatus.BAD_REQUEST);
        }
        if (user.getLastname().isBlank() || user.getLastname().equals("") || user.getLastname().isEmpty()){
            return new ResponseEntity<>(Map.of("error", "lastname not valid"), HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword().isBlank() || user.getPassword().equals("") || user.getPassword().isEmpty() ){
            return new ResponseEntity<>(Map.of("error", "email not valid"), HttpStatus.BAD_REQUEST);
        }
        if (user.getName().isBlank() || user.getName().equals("") || user.getName().isEmpty()){
            return new ResponseEntity<>(Map.of("error", "email not valid"), HttpStatus.BAD_REQUEST);
        }
        //return new ResponseEntity<>(user, HttpStatus.OK);
        //store.getUserStore().put(user.getEmail(), user);
        id++;
        user.setPassword(b.getEncoder().encode(user.getPassword()));
        user.setId(id);
        if (userRepo.hasUser(user)) {
            return new ResponseEntity<>(Map.of("message", "User exist!", "error","Bad Request","status", 400), HttpStatus.BAD_REQUEST);
        }else{
            userRepo.save(user);
        }


        return new ResponseEntity<>(Map.of("id", user.getId(), "name", user.getName(), "lastname", user.getLastname(), "email", user.getEmail()), HttpStatus.OK);
    }

    @PostMapping("api/auth/changepass")
    public String changePassword() {
        return "changePassword";
    }
}
