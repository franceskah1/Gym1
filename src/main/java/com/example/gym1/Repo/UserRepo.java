package com.example.gym1.Repo;

import com.example.gym1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    User findByPasswordToken(String token);
}
