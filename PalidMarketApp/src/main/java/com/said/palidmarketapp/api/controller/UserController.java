package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.UserService;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserLoginDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController  {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserSaveDto> saveUser(@RequestBody UserSaveDto userSaveDto){
        UserSaveDto resultUser = userService.saveUser(userSaveDto);
        return ResponseEntity.ok(resultUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> loginUser(@RequestBody UserLoginDto userLoginDto){
        UserLoginDto resultUser = userService.loginUser(userLoginDto);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAll(){
        List <UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

}
