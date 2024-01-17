package com.said.palidmarketapp.business.concretes.auth;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserMapper userMapper;
    public void register(UserRegisterRequestDto requestDto) {
        var user = UserRegisterRequestDto.builder()
                .phoneNumber(requestDto.getPhoneNumber())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .roles(requestDto.getRoles())
                .build();

        userRepository.save(userMapper.mapRegisterRequestDtoToEntity(user));
    }

    public AuthenticationDto authenticate(AuthRequestDto authRequestDto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getPhoneNumber(),
                        authRequestDto.getPassword()
                )
        );
        User user = userRepository.findByPhoneNumber(authRequestDto.getPhoneNumber());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDto.builder()
                .token(jwtToken)
                .build();
    }

    public void deleteUser(Integer userId) {
        log.info("ActionLog.deleteUser.start");
        userRepository.deleteById(userId);
        log.info("ActionLog.deleteUser.end");
    }

    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}