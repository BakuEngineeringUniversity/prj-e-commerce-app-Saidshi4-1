package com.said.palidmarketapp.api.controller.auth;

import com.said.palidmarketapp.business.concretes.auth.AuthService;
import com.said.palidmarketapp.mapper.dto.auth.AuthRequestDto;
import com.said.palidmarketapp.mapper.dto.auth.AuthenticationDto;
import com.said.palidmarketapp.mapper.dto.auth.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(
            @RequestBody UserRegisterRequestDto requestDto
    ) {
        authService.register(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(
            @RequestBody AuthRequestDto authRequestDto
    ) {
        return ResponseEntity.ok(authService.authenticate(authRequestDto));
    }
    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deleteUser(@PathVariable Integer userId){
        authService.deleteUser(userId);
    }
}