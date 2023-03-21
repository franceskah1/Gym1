package com.example.gym1.Model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String className;
    private String classDescription;
    private int classCapacity;
    private LocalDateTime start_date;
    private LocalDateTime end_date;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tranier_id",referencedColumnName = "id")
    private Tranier tranier;

}


