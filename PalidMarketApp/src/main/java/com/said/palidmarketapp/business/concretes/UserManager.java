package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.UserService;
import com.said.palidmarketapp.core.utilities.results.*;
import com.said.palidmarketapp.dataAccess.abstracts.UserDao;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserLoginDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public DataResult <UserSaveDto> saveUser(UserSaveDto userSaveDto) {
        log.info("application.saveUser.start");
        String phoneNumber = userSaveDto.getPhoneNumber();

        boolean phoneNumberExists = userDao.existsByPhoneNumber(phoneNumber);

        if (phoneNumberExists) {
            throw new IllegalArgumentException("This phone number is already in use.");
        } else {
            User user = modelMapper.map(userSaveDto, User.class);
            log.info("application.saveUser.end");
            return new SuccessDataResult<>(modelMapper.map(userDao.save(user), UserSaveDto.class), "User saved successfully");
        }
    }

    @Override
    public UserLoginDto loginUser(UserLoginDto userLoginDto) {
        log.info("application.loginUser.start");
        String phoneNumber = userLoginDto.getPhoneNumber();
        String password = userLoginDto.getPassword();

        boolean userExists = userDao.existsByPhoneNumberAndPassword(phoneNumber, password);

        if (userExists){
            User user = modelMapper.map(userLoginDto, User.class);
            log.info("application.loginUser.end");
            return modelMapper.map(userDao.findOneByPhoneNumberAndPassword(user.getPhoneNumber(), user.getPassword()), UserLoginDto.class);
        }
        else throw new IllegalArgumentException("Wrong phone number or password");
    }

    @Override
    public DataResult<List<UserDto>> getAll() {
        log.info("application.getAllUser.start");
        List <User> users = userDao.findAll();
        log.info("application.getAllUser.end");
        return new SuccessDataResult<>(users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList()), "All user list");
    }

    @Transactional
    public Result deleteUserByPhoneNumber(String phoneNumber) {
        log.info("application.deleteUserByPhoneNumber.start");
        if (userDao.existsByPhoneNumber(phoneNumber)) {
            userDao.deleteByPhoneNumber(phoneNumber);
            log.info("application.deleteUserByPhoneNumber.end.successfully");
            return new SuccessResult("User deleted successfully");
        } else {
            log.info("application.deleteUserByPhoneNumber.end.unsuccessfully");
            return new ErrorResult("User not found with the given phone number");
        }

    }

    @Override
    public Result updateUser(UserSaveDto newUser){
        log.info("application.updateUser.start");
        User user = userDao.findByPhoneNumber(newUser.getPhoneNumber());
        if(user != null){
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(newUser.getPassword());
            userDao.saveAndFlush(user);
            log.info("application.updateUser.end.successfully");
            return new SuccessDataResult<>(user, "Updating is successfully");
        }
        log.info("application.updateUser.end.unsuccessfully");
        return new ErrorDataResult<User>(user, "User does not exists");
    }

    @Override
    public Integer findIdByPhoneNumber(String phoneNumber) {
        return userDao.findIdByPhoneNumber(phoneNumber);
    }

    @Override
    public Result updateFirstName(String phoneNumber, String newFirstName) {
        log.info("application.updateFirstName.start");
        User user = userDao.findByPhoneNumber(phoneNumber);
        if(user != null){
            user.setFirstName(newFirstName);
            userDao.saveAndFlush(user);
            log.info("application.updateFirstName.end.successfully");
            return new SuccessResult("Update is successfully");
        }
        else {
            log.info("application.updateFirstName.end.unsuccessfully");
            return new ErrorResult("User does not exists");
        }
    }

    @Override
    public Result updateLastName(String phoneNumber, String newLastName) {
        log.info("application.updateLastName.start");
        User user = userDao.findByPhoneNumber(phoneNumber);

        if(user != null){
            user.setLastName(newLastName);
            userDao.saveAndFlush(user);
            log.info("application.updateLastName.end.successfully");
            return new SuccessResult("Update is successfully");
        }
        else {
            log.info("application.updateLastName.end.unsuccessfully");
            return new ErrorResult("User does not exists");
        }
    }
}