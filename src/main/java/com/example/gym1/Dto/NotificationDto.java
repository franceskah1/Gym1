package com.example.gym1.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto{
    private Long notifyAdminId;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String userName;
    private String event;
    LocalDateTime localDateTime ;
    private int duration_months;

    private String memberShipType;
}
