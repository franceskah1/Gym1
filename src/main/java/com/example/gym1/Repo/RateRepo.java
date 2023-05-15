package com.example.gym1.Repo;

import com.example.gym1.Model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepo extends JpaRepository<Rate,Long> {

    Optional<Rate> findByUserId(Long userId);
}
