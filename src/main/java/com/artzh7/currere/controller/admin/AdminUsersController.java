package com.artzh7.currere.controller.admin;

import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import com.artzh7.currere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
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
        boolean successfulUserSaveMain = userService.userSaveMain(user, username, password1, password2, role);
        if (!successfulUserSaveMain) {
            model.addAttribute("message", "Пароли не совпадают");
            model.addAttribute("user", user);
            model.addAttribute("roles", UserRole.values());
            return "admin/userEdit";
        } else {
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/saveDetails")
    public String userSaveDetails(
            @RequestParam("userId") User user,
            @RequestParam String displayedName,
            @RequestParam String address,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) String comment) {
        userService.userSaveDetails(user, displayedName, address, phoneNumber, comment);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete")
    public String userDelete(@RequestParam("userId") User user) {
        userService.delete(user);
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
        boolean successfulUserAdd = userService.userAdd(user);
        if (!successfulUserAdd) {
            model.addAttribute("message", "Пользователь существует!");
            return "admin/userAdd";
        } else {
            return "redirect:/admin/users";
        }
    }
}
