package com.said.palidmarketapp.mapper.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private Integer id;
    private String phoneNumber;
    private String password;
}
