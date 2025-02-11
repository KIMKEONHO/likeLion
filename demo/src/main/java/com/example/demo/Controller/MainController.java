package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "main/login";
    }

    @PostMapping("/login")
    public String loginPost() {

        return "main/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "main/signup";
    }


}
