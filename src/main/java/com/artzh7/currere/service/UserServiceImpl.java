package com.artzh7.currere.service;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import com.artzh7.currere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getWorkingCouriers() {
        return userRepo.findAllByRoleAndWorking(UserRole.COURIER, true);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void shift(User user, OrderService orderService) throws Exception {
        if (user.isWorking()) {
            List<Order> currentOrders = orderService.getCourierCurrentOrderList(user);
            if (currentOrders.isEmpty()) {
                user.setWorking(false);
            } else {
                throw new Exception("Нельзя закрыть смену, пока выполняются заказы");
            }
        } else {
            user.setWorking(true);
        }
        userRepo.save(user);
    }
}
