package com.artzh7.currere.service;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import com.artzh7.currere.repo.OrderRepo;
import com.artzh7.currere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Order> getOrderList(String orderStatus) {
        if (orderStatus.isEmpty()) {
            return orderRepo.findAllByOrderByIdDesc();
        } else {
            return orderRepo.findAllByOrderStatusOrderByIdDesc(
                    OrderStatus.valueOf(orderStatus));
        }
    }

    @Override
    public List<Order> getRestaurantOrderList(User author, String orderStatus) {
        if (orderStatus.isEmpty()) {
            return orderRepo.findAllByAuthorOrderByIdDesc(author);
        } else {
            return orderRepo.findAllByAuthorAndOrderStatusOrderByIdDesc(
                    author, OrderStatus.valueOf(orderStatus));
        }
    }

    @Override
    public List<Order> getCourierCurrentOrderList(User courier) {
        Set<OrderStatus> ignoringOrderStatuses = Set.of(OrderStatus.CANCELLED, OrderStatus.FINISHED);
        return orderRepo.findAllByCourierAndOrderStatusNotIn(courier, ignoringOrderStatuses);
    }

    @Override
    public List<Order> getCourierFinishedOrderList(User courier) {
        return orderRepo.findAllByCourierAndOrderStatusOrderByIdDesc(courier, OrderStatus.FINISHED);
    }

    @Override
    public Order getOrder(Long id) {
        Optional<Order> order = orderRepo.findById(id);
        return order.orElse(null);
    }

    @Override
    public Order getOrder(User user, Long id) {
        Optional<Order> optional = orderRepo.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            if (user.getRole() == UserRole.COURIER
                    && order.getCourier().getId().equals(user.getId())) {
                return order;
            } else if (user.getRole() == UserRole.RESTAURANT
                    && order.getAuthor().getId().equals(user.getId())) {
                return order;
            } else return null;
        }
        return null;
    }

    @Override
    public void create(Order order) {
        orderRepo.save(order);
    }

    @Override
    public void appoint(Order order) {
        List<User> workingCouriers = userRepo.findAllByRoleAndWorking(UserRole.COURIER, true);
        if (!workingCouriers.isEmpty()) {
            int randomIndex = (int) (Math.random() * workingCouriers.size());
            order.setCourier(workingCouriers.get(randomIndex));
            order.setOrderStatus(OrderStatus.IN_WORK);
            orderRepo.save(order);
        }
    }

    @Override
    public void cancel(Order order) {
        order.setOrderStatus(OrderStatus.CANCELLED);
        order.setCourier(null);
        orderRepo.save(order);
    }
}
