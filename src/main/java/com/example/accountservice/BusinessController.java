package com.example.accountservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class BusinessController {

    @GetMapping("api/empl/payment")
    public ResponseEntity<Object> getPayrolls(@AuthenticationPrincipal UserDetailsImpl details) {
        if (details == null) {
            return new ResponseEntity<>(Map.of("error", "email not valid"), HttpStatus.BAD_REQUEST);
        } else {
            User user = details.getUser();
            return new ResponseEntity<>(Map.of("id", user.getId(), "name", user.getName(), "lastname", user.getLastname(), "email", user.getEmail()), HttpStatus.OK);
        }
    }

    @PostMapping("api/acct/payments")
    public String addPayrolls() {
        return "addPayrolls";
    }

    @PutMapping("api/acct/payments")
    public String updatePayrolls() {
        return "updatePayrolls";
    }
}
