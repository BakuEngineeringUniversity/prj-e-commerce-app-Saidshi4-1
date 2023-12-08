package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.UserService;
import com.said.palidmarketapp.dataAccess.abstracts.UserDao;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserLoginDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public UserSaveDto saveUser(UserSaveDto userSaveDto) {
        User user = modelMapper.map(userSaveDto, User.class);
        return modelMapper.map(userDao.save(user), UserSaveDto.class);
    }

    @Override
    public UserLoginDto loginUser(UserLoginDto userLoginDto) {
        User user = modelMapper.map(userLoginDto, User.class);
        return modelMapper.map(userDao.findOneByPhoneNumberAndPassword(user.getPhoneNumber(), user.getPassword()), UserLoginDto.class);
    }


    @Override
    public List<UserDto> getAll() {
        List <User> users = userDao.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
