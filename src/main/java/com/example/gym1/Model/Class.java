package com.example.gym1.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Setter
@Getter
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
    @JsonSerialize(as = LocalDateTime.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "")
    private LocalDateTime start_date;
    @JsonSerialize(as = LocalDateTime.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "")
    private LocalDateTime end_date;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id",referencedColumnName = "id")
    private Staff staff;

}


