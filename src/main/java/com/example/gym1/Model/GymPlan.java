package com.example.gym1.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GymPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String plan_name;
    private String description;
    private double price;
    private int duration_months;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "gymPlan")
    private List<MemberShip> memberShips ;
    //when user delete membership at the same time he delete gymplan
}

