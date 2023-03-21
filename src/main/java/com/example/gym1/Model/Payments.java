package com.example.gym1.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private double amount;
    private LocalDate dateOfPayment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberShip_id",referencedColumnName = "id")
    private MemberShip memberShip;

}