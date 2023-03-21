package com.example.gym1.Repo;

import com.example.gym1.Model.NotifyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyRepo extends JpaRepository<NotifyAdmin,Long> {
}
