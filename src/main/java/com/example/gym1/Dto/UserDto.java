package com.example.gym1.Dto;

import com.example.gym1.Model.Gender;
import com.example.gym1.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {

        private String userName;
        private String lastName;
        private String address;
        private String phoneNumber;
        private String email;

    }

