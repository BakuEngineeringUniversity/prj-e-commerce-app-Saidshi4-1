package com.said.palidmarketapp.mapper.dto.auth;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {
    private String token;
}
