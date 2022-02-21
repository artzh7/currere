package com.artzh7.currere.controller.courier;

import com.artzh7.currere.entity.User;
import com.artzh7.currere.service.OrderService;
import com.artzh7.currere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courier")
public class CourierMainController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public String main(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("courier", user);
        return "courier/main";
    }

    @PostMapping("/shift")
    public String shift(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("courier", user);
        try {
            userService.shift(user, orderService);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "courier/main";
    }
}
