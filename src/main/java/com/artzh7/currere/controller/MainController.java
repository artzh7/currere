package com.artzh7.currere.controller;

import com.artzh7.currere.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping()
    public String main(@AuthenticationPrincipal User user) {
        switch (user.getRole()) {
            case ADMIN:
                return "redirect:/admin";
            case RESTAURANT:
                return "redirect:/restaurant";
            case COURIER:
                return "redirect:/courier";
            default:
                return "";
        }
    }
}
