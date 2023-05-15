package com.example.gym1.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Long id ;
    private  String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate hiredDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private double  Salary;
    private  String job_title;







}
