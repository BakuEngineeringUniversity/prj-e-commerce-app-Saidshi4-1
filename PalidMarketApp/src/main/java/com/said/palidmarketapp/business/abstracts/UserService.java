package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.core.utilities.results.SuccessDataResult;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserLoginDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;

import java.util.List;

public interface UserService {
    DataResult <UserSaveDto> saveUser(UserSaveDto userSaveDto);
    DataResult <UserLoginDto> loginUser(UserLoginDto userLoginDto);
    DataResult <List <UserDto>> getAll();
    Result deleteUserByPhoneNumber(String phoneNumber);
    Result updateFirstName(String phoneNumber, String newFirstName);
    Result updateLastName(String phoneNumber, String newLastName);
    Result  updateUser(UserSaveDto newUser);
}
