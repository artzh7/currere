package com.artzh7.currere.controller.restaurant;

import com.artzh7.currere.entity.User;
import com.artzh7.currere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/restaurant/profile")
public class RestaurantProfileController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String profileEdit(@AuthenticationPrincipal User user, Model model) {
        User userToEdit = userRepo.findByUsername(user.getUsername());
        model.addAttribute("user", userToEdit);
        return "restaurant/profileEdit";
    }

    @PostMapping
    public String profileSave(
            @RequestParam String displayedName,
            @RequestParam String address,
            @RequestParam String comment,
            @RequestParam("userId") User user
    ) {
        user.setDisplayedName(displayedName);
        user.setAddress(address);
        user.setComment(comment);
        userRepo.save(user);
        return "redirect:/restaurant";
    }
}
