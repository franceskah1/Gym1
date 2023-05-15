package com.example.gym1.Repo;

import com.example.gym1.Model.GymPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepo extends JpaRepository<GymPlan ,Long> {
}
