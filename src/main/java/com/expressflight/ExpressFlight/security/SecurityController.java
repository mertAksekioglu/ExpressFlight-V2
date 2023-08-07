package com.expressflight.ExpressFlight.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SecurityController {

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}