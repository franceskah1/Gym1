package com.example.gym1.Repo;

import com.example.gym1.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByName(String user);
}


