package com.example.gym1.Repo;

import com.example.gym1.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepo extends JpaRepository<Staff,Long> {
    boolean existsByEmail(String email);


    Optional<Staff> findByEmail(String email);

    Optional<Staff>findUserByEmail(String email);



}
