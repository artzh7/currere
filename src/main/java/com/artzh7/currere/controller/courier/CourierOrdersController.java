package com.artzh7.currere.controller.courier;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/courier/orders")
public class CourierOrdersController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String listCurrentCourierOrders(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.getCourierCurrentOrderList(user);
        model.addAttribute("orders", orders);
        model.addAttribute("orderListTitle", "Текущие заказы");
        return "courier/orders";
    }

    @GetMapping("/history")
    public String listFinishedCourierOrders(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.getCourierFinishedOrderList(user);
        model.addAttribute("orders", orders);
        model.addAttribute("orderListTitle", "История заказов");
        return "courier/orders";
    }

    @GetMapping("/{id}")
    public String orderInfo(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) {
        Order order = orderService.getOrder(user, id);
        if (order == null)
            return "redirect:/courier/orders";

        model.addAttribute("order", order);
        return "courier/orderInfo";
    }
}
