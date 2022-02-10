package com.artzh7.currere.controller.admin;

import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import com.artzh7.currere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "admin/users";
    }

    @PostMapping()
    public String userSaveMain(
            @RequestParam String username,
            @RequestParam("newPassword") String password1,
            @RequestParam("confirmPassword") String password2,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            Model model) {
        if (!password1.isBlank() && !password2.isBlank()) {
            if (!password1.equals(password2)) {
                model.addAttribute("message", "Пароли не совпадают");
                model.addAttribute("user", user);
                model.addAttribute("roles", UserRole.values());
                return "admin/userEdit";
            } else {
                user.setPassword(password1);
            }
        }
        user.setUsername(username);

        Set<String> roles = Arrays.stream(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(UserRole.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/saveDetails")
    public String userSaveDetails(
            @RequestParam("userId") User user,
            @RequestParam String displayedName,
            @RequestParam String address,
            @RequestParam String comment) {
        user.setDisplayedName(displayedName);
        user.setAddress(address);
        user.setComment(comment);
        userRepo.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        return "admin/userEdit";
    }

    @GetMapping("/add")
    public String userAddForm() {
        return "admin/userAdd";
    }

    @PostMapping("/add")
    public String userAdd(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "Пользователь существует!");
            return "admin/userAdd";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(UserRole.USER));
        userRepo.save(user);
        return "redirect:/admin/users";
    }
}
