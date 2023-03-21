package com.example.gym1.Dto;

import com.example.gym1.Model.Gender;
import com.example.gym1.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserRegistrationDto {
    private String userName;
    private String lastName;
    private int age;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private Set<Role> roles;
}