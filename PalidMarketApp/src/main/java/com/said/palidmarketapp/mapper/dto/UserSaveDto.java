package com.said.palidmarketapp.mapper.dto;

import lombok.Data;

@Data
public class UserSaveDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
