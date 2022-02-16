package com.artzh7.currere.repo;

import com.artzh7.currere.entity.User;
import com.artzh7.currere.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByRoleAndWorking(UserRole role, boolean working);
}
