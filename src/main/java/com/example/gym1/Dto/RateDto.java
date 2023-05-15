package com.example.gym1.Dto;

import lombok.Data;

@Data
public class RateDto {
    private Long id;
    private String comment;
    private int rate;
  private Long userId;
  private UserDto userDto;

}
