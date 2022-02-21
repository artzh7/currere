package com.artzh7.currere.service;

import com.artzh7.currere.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    List<User> getWorkingCouriers();

    void save(User user);
    void delete(User user);
    void shift(User user, OrderService orderService) throws Exception;
}
