package com.artzh7.currere.repo;

import com.artzh7.currere.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
}
