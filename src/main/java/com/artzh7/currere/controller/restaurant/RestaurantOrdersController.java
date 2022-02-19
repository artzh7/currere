package com.artzh7.currere.controller.restaurant;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurant/orders")
public class RestaurantOrdersController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String orderList(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String orderStatus,
            Model model) {
        List<Order> orders = orderService.getRestaurantOrderList(user, orderStatus);
        model.addAttribute("orders", orders);

        OrderStatus[] statuses = OrderStatus.values();
        model.addAttribute("statuses", statuses);
        return "restaurant/orders";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String clientAddress,
            @RequestParam String clientPhoneNumber,
            @RequestParam String orderComment) {
        Order order = new Order(user,
                user.getDisplayedName(), user.getAddress(),
                user.getPhoneNumber(), user.getComment(),
                clientAddress, clientPhoneNumber, orderComment);
        orderService.create(order);
        return "redirect:/restaurant/orders";
    }

    @GetMapping("/{id}")
    public String orderInfo(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) {
        Order order = orderService.getOrder(user, id);
        if (order == null)
            return "redirect:/restaurant/orders";

        model.addAttribute("order", order);
        return "restaurant/orderInfo";
    }
}
