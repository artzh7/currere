package com.artzh7.currere.controller.admin;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import com.artzh7.currere.repo.OrderRepo;
import com.artzh7.currere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrdersController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping()
    public String orderList(
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
        return "admin/orders";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String restaurantName,
            @RequestParam String clientAddress,
            @RequestParam String clientPhoneNumber,
            @RequestParam String orderComment) {
        Order order = new Order(user, restaurantName, clientAddress, clientPhoneNumber, orderComment);
        orderRepo.save(order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/appoint")
    public String appoint(@RequestParam(name = "orderId") Order order) {
        List<User> workingCouriers = userRepo.findAllByRoleAndWorking(UserRole.COURIER, true);
        if (workingCouriers.isEmpty())
            return "redirect:/admin/orders";
        int randomIndex = (int) (Math.random() * workingCouriers.size());
        order.setCourier(workingCouriers.get(randomIndex));
        order.setOrderStatus(OrderStatus.IN_WORK);
        orderRepo.save(order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/cancel")
    public String cancel(@RequestParam(name = "orderId") Order order) {
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
        return "redirect:/admin/orders";
    }
}
