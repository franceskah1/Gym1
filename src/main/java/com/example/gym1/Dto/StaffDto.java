package com.example.gym1.Dto;

import com.example.gym1.Model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component

public class StaffDto {
    private Long id;
    private  String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate hiredDate;
    private String gender;
    private double  Salary;
    private  String job_title;
}
