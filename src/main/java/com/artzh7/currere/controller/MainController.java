package com.artzh7.currere.controller;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String orderStatus,
            Model model) {
        List<Order> orders;
        if (orderStatus.isEmpty()) {
            orders = orderRepo.findAllByOrderByIdDesc();
        } else {
            orders = orderRepo.findAllByOrderStatusOrderByIdDesc(
                    OrderStatus.valueOf(orderStatus));
        }
        model.addAttribute("orders", orders);

        OrderStatus[] statuses = OrderStatus.values();
        model.addAttribute("statuses", statuses);
        return "main";
    }

    @PostMapping("add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String restaurantName,
            @RequestParam String clientAddress,
            @RequestParam String clientPhoneNumber) {
        Order booking = new Order(user, restaurantName, clientAddress, clientPhoneNumber);
        orderRepo.save(booking);
        return "redirect:/main";
    }

    @GetMapping("/")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
