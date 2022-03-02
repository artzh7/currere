package com.artzh7.currere.service;

import com.artzh7.currere.entity.Order;
import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import com.artzh7.currere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public boolean userSaveMain(User user, String username, String password1, String password2, String role) {
        if (!password1.isBlank() && !password2.isBlank()) {
            if (!password1.equals(password2)) {
                return false;
            } else {
                user.setPassword(passwordEncoder.encode(password1));
            }
        }
        user.setUsername(username);
        user.setRole(UserRole.valueOf(role));
        userRepo.save(user);
        return true;
    }

    @Override
    public void userSaveDetails(User user, String displayedName, String address, String phoneNumber, String comment) {
        user.setDisplayedName(displayedName);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setComment(comment);
        userRepo.save(user);
    }

    @Override
    public boolean userAdd(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        user.setActive(true);
        user.setWorking(false);
        userRepo.save(user);
        return true;
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
