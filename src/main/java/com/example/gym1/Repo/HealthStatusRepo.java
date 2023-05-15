package com.example.gym1.Repo;

import com.example.gym1.Model.HealthStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthStatusRepo extends JpaRepository<HealthStatus,Long> {

    Optional<HealthStatus> findByUserId(Long userId);
}
