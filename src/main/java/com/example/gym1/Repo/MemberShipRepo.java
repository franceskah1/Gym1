package com.example.gym1.Repo;

import com.example.gym1.Model.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberShipRepo extends JpaRepository<MemberShip,Long> {
    void deleteMemberShipByid(Long id);
}
