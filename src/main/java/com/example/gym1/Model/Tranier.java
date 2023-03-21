package com.example.gym1.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tranier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Long id ;

    private  String firstName;
    private String lastName;
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
    private double  Salary;
    private String phoneNumber;
    private String email;
    private LocalDate hiredDate;
    @OneToOne(mappedBy = "tranier")
    private Class aClass;


}
