package com.example.gym1.Dto;

import lombok.Data;

import java.time.LocalDateTime;
    @Data
    public class AttendanceDto {
    private Long id;
    private LocalDateTime check_in;
    private LocalDateTime check_out;
    private UserDto userDto;
    private String className;


}
