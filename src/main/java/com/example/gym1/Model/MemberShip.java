package com.example.gym1.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberShip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String memberShipType;

    private LocalDateTime start_date;
    private LocalDateTime end_date;

    //private LocalDate expire_date= LocalDate.of(2023, 6, 30);;

    private boolean status;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public  User user;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gymPlan_id")
    public  GymPlan gymPlan;



    @OneToMany(mappedBy = "memberShip", cascade = CascadeType.REMOVE)
    private List<NotifyAdmin> notifyAdmins ;
}
