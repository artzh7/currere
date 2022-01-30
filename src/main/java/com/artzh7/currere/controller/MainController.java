package com.artzh7.currere.controller;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import com.artzh7.currere.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "ALL") String orderStatus,
                       Map<String, Object> model) {
        List<Order> orders = new ArrayList<>();
        try {
            if (orderStatus.equals("WORKING")) {
                orders.addAll(orderRepo.findAllByOrderStatus(OrderStatus.IN_WORK));
                orders.addAll(orderRepo.findAllByOrderStatus(OrderStatus.COURIER_ARRIVED));
                orders.addAll(orderRepo.findAllByOrderStatus(OrderStatus.IN_TRANSIT));
            } else {
                orders.addAll(orderRepo.findAllByOrderStatus(OrderStatus.valueOf(orderStatus)));
            }
        } catch (IllegalArgumentException e) {
            orders.addAll(orderRepo.findAll());
        }
        model.put("orders", orders);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam String restaurantName,
            @RequestParam String clientAddress,
            @RequestParam String clientPhoneNumber) {
        Order booking = new Order(restaurantName, clientAddress, clientPhoneNumber);
        orderRepo.save(booking);
        return "redirect:/main";
    }

    @GetMapping("/")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }
}
