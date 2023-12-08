package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserLoginDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;
import io.swagger.v3.oas.models.responses.ApiResponse;

import java.util.List;

public interface UserService {
    UserSaveDto saveUser(UserSaveDto userSaveDto);
    UserLoginDto loginUser(UserLoginDto userLoginDto);
    List <UserDto> getAll();
}
