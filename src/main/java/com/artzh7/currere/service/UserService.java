package com.artzh7.currere.service;

import com.artzh7.currere.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    List<User> getWorkingCouriers();

    void save(User user);

    boolean userSaveMain(User user, String username, String password1, String password2, String role);
    void userSaveDetails(User user, String displayedName, String address, String phoneNumber, String comment);
    boolean userAdd(User user);

    void delete(User user);
    void shift(User user, OrderService orderService) throws Exception;
}
