package com.example.gym1.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
public class HealthStatusDto {
    private  Long id;
    private double height;
    private double weight;
    private double body_fat_percentage;
    private String remarks;
    private UserDto userDto;
}
