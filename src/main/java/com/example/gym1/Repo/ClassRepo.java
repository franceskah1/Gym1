package com.example.gym1.Repo;

import com.example.gym1.Model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<Class,Long> {
}
