package com.said.palidmarketapp.mapper.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private Set<RoleDto> roles;

}