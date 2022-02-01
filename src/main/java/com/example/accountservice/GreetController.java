package com.example.accountservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetController {

    @GetMapping(value="/greet", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String greetUser() {
        return "<html><p>Hello and welcome!</p></html>";
    }
}
