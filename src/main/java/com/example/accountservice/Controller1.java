package com.example.accountservice;


import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;

@RestController
@Validated
public class Controller1 {

    @GetMapping("/test/{id}")
    public int test(@PathVariable @Min(1) @Max(100) int id) {
        return id;
    }

}
