package com.example.gym1.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotifyAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
private Long id;
    private int duration_months;

    private String memberShipType;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    LocalDateTime lt
            = LocalDateTime.now();
    private String event;



    @ManyToOne
    @JoinColumn(name = "memberShip_id",nullable = true)
    private MemberShip memberShip;





}

