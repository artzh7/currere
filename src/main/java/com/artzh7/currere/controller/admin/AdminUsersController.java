package com.artzh7.currere.controller.admin;

import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import com.artzh7.currere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam String role,
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
        user.setRole(UserRole.valueOf(role));

        userRepo.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/saveDetails")
    public String userSaveDetails(
            @RequestParam("userId") User user,
            @RequestParam String displayedName,
            @RequestParam String address,
            @RequestParam String phoneNumber,
            @RequestParam String comment) {
        user.setDisplayedName(displayedName);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setComment(comment);
        userRepo.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete")
    public String userDelete(@RequestParam("userId") User user) {
        userRepo.delete(user);
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
        user.setRole(UserRole.USER);
        user.setActive(true);
        user.setWorking(false);
        userRepo.save(user);
        return "redirect:/admin/users";
    }
}
