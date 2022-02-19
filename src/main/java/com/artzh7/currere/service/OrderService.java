package com.artzh7.currere.service;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList(String orderStatus);
    List<Order> getRestaurantOrderList(User author, String orderStatus);
    List<Order> getCourierCurrentOrderList(User courier);
    List<Order> getCourierFinishedOrderList(User courier);

    Order getOrder(Long id);
    Order getOrder(User user, Long id);

    void create(Order order);
    void appoint(Order order);
    void cancel(Order order);
}
