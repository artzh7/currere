package com.artzh7.currere.repo;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import com.artzh7.currere.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
    // все заказы в порядке убывания ID
    List<Order> findAllByOrderByIdDesc();

    // все заказы с указанным статусом в порядке убывания ID
    List<Order> findAllByOrderStatusOrderByIdDesc(OrderStatus orderStatus);

    // все заказы ресторана в порядке убывания ID
    List<Order> findAllByAuthorOrderByIdDesc(User user);

    // все заказы ресторана с указанным статусом в порядке убывания ID
    List<Order> findAllByAuthorAndOrderStatusOrderByIdDesc(User user, OrderStatus orderStatus);
}
