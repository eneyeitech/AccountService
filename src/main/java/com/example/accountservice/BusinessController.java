package com.example.accountservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @GetMapping("api/empl/payment")
    public String getPayrolls() {
        return "getPayrolls";
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
