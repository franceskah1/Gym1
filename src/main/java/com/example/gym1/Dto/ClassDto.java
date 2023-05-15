package com.example.gym1.Dto;

import com.example.gym1.Model.Staff;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassDto {
    Long id;
    private String className;
    private String classDescription;
    private int classCapacity;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String dayOfWeek;
    String userName;
    private StaffDetailsDto staffDetailsDto;
    private Staff staff;

}
