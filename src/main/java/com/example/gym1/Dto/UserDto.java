package com.example.gym1.Dto;
import com.example.gym1.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {
private Long id;
        private String userName;
        private String lastName;
        private String address;
        private String phoneNumber;
        private String email;
        private String password;
        private int age;
        private String gender;
        private Set<RoleDto> roles;

    }

