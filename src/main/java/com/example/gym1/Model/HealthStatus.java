package com.example.gym1.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HealthStatus {

    @Id
    @GeneratedValue
    private  Long id;
    private double height;
    private double weight;
    private double body_fat_percentage;
    private String remarks;
 //   double BMI = weight / (height * height);
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    }
