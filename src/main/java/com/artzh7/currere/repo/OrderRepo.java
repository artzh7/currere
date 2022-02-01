package com.artzh7.currere.repo;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
    List<Order> findAllByOrderByIdDesc();
    List<Order> findAllByOrderStatusOrderByIdDesc(OrderStatus orderStatus);
}
