package com.artzh7.currere.controller.restaurant;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/restaurant/orders")
public class RestaurantOrdersController {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping()
    public String orderList(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String orderStatus,
            Model model) {
        List<Order> orders;
        if (orderStatus.isEmpty()) {
            orders = orderRepo.findAllByAuthorOrderByIdDesc(user);
        } else {
            orders = orderRepo.findAllByAuthorAndOrderStatusOrderByIdDesc(
                    user, OrderStatus.valueOf(orderStatus));
        }
        model.addAttribute("orders", orders);

        OrderStatus[] statuses = OrderStatus.values();
        model.addAttribute("statuses", statuses);
        return "restaurant/orders";
    }
}
