package com.example.gym1.Repo;
import com.example.gym1.Model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payments,Long> {
  Optional<Payments>  findByUserId(Long memberShipId);

}
