package com.said.palidmarketapp.business.concretes.auth;

import com.said.palidmarketapp.core.utilities.results.*;
import com.said.palidmarketapp.dataAccess.abstracts.UserDao;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.auth.AuthRequestDto;
import com.said.palidmarketapp.mapper.dto.auth.AuthenticationDto;
import com.said.palidmarketapp.mapper.dto.auth.UserRegisterRequestDto;
import com.said.palidmarketapp.mapstruct.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserMapper userMapper;
    public Result register(UserRegisterRequestDto requestDto) {
        log.info("authService.register.start");
        if (!userRepository.existsByPhoneNumber(requestDto.getPhoneNumber())){
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            User user = userMapper.mapRegisterRequestDtoToEntity(requestDto);
            userRepository.save(user);
            log.info("authService.register.end.successfully");

            return new SuccessResult("User register successfully");
        }else {
            log.info("authService.register.end.unsuccessfully");
            return new ErrorResult("Phone number already exist");
        }
    }

    public DataResult<AuthenticationDto> authenticate(AuthRequestDto authRequestDto) {
        if (userRepository.existsByPhoneNumber(authRequestDto.getPhoneNumber())){
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDto.getPhoneNumber(),
                            authRequestDto.getPassword()
                    )
            );
            User user = userRepository.findUserByPhoneNumber(authRequestDto.getPhoneNumber());
            var jwtToken = jwtService.generateToken(user);
            return new SuccessDataResult<>(AuthenticationDto.builder().token(jwtToken).build(), "Getting of token is successfully");
        }
        else {
            return new ErrorDataResult<>(null, "User does not exist by phone number");
        }
    }
}