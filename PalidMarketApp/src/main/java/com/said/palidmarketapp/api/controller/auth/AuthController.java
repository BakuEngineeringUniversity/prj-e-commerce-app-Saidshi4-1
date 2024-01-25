package com.said.palidmarketapp.api.controller.auth;

import com.said.palidmarketapp.business.concretes.auth.AuthService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.mapper.dto.auth.AuthRequestDto;
import com.said.palidmarketapp.mapper.dto.auth.AuthenticationDto;
import com.said.palidmarketapp.mapper.dto.auth.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/auth/register")
    public ResponseEntity<Result> register(@RequestBody UserRegisterRequestDto requestDto) {
        try {
            Result result = authService.register(requestDto);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/v1/auth/login")
    public ResponseEntity<DataResult<AuthenticationDto>> login(@RequestBody AuthRequestDto authRequestDto) {
        try {
            DataResult<AuthenticationDto> result = authService.authenticate(authRequestDto);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}