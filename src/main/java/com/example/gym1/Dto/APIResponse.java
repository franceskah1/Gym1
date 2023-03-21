package com.example.gym1.Dto;

import com.example.gym1.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIResponse {
    private String accessToken;
    private User userData;

    public APIResponse() {

    }
}

