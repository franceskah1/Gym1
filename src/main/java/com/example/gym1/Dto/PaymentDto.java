package com.example.gym1.Dto;

import com.example.gym1.Model.MemberShip;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class PaymentDto {
    long id;
    private double amount;
    private LocalDate dateOfPayment;
    private  UserDto user;



    }


