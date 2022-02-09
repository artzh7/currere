package com.artzh7.currere.controller;

import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class MainController {
    @GetMapping()
    public String main(@AuthenticationPrincipal User user) {
        Set<UserRole> roles = user.getRoles();
        if (roles.contains(UserRole.ADMIN)) {
            return "redirect:/admin";
        } else if (roles.contains(UserRole.RESTAURANT)) {
            return "redirect:/restaurant";
        } else if (roles.contains(UserRole.COURIER)) {
            return "redirect:/courier";
        } else return "";
    }
}
