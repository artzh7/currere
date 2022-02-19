package com.artzh7.currere.controller.admin;

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
@RequestMapping("/admin/orders")
public class AdminOrdersController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String orderList(
            @RequestParam(required = false, defaultValue = "") String orderStatus,
            Model model) {
        List<Order> orders = orderService.getOrderList(orderStatus);
        model.addAttribute("orders", orders);

        OrderStatus[] statuses = OrderStatus.values();
        model.addAttribute("statuses", statuses);
        return "admin/orders";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User author,
            @RequestParam String restaurantName, @RequestParam String restaurantAddress,
            @RequestParam String restaurantPhoneNumber, @RequestParam String restaurantComment,
            @RequestParam String clientAddress, @RequestParam String clientPhoneNumber,
            @RequestParam String orderComment) {
        Order order = new Order(author,
                restaurantName, restaurantAddress, restaurantPhoneNumber, restaurantComment,
                clientAddress, clientPhoneNumber, orderComment);
        orderService.create(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/{id}")
    public String orderInfo(@PathVariable Long id, Model model) {
        Order order = orderService.getOrder(id);
        if (order == null)
            return "redirect:/admin/orders";

        model.addAttribute("order", order);
        return "admin/orderInfo";
    }

    @PostMapping("/appoint")
    public String appoint(@RequestParam(name = "orderId") Order order) {
        orderService.appoint(order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/cancel")
    public String cancel(@RequestParam(name = "orderId") Order order) {
        orderService.cancel(order);
        return "redirect:/admin/orders";
    }
}
