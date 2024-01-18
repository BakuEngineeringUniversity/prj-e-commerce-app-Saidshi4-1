package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.UserService;
import com.said.palidmarketapp.core.utilities.results.*;
import com.said.palidmarketapp.dataAccess.abstracts.RoleDao;
import com.said.palidmarketapp.dataAccess.abstracts.UserDao;
import com.said.palidmarketapp.entities.Role;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserLoginDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;
import com.said.palidmarketapp.mapper.dto.auth.RoleDto;
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
    private final RoleDao roleDao;
    private final ModelMapper modelMapper;

    @Override
    public DataResult<List<UserDto>> getAll() {
        log.info("application.getAllUser.start");
        List <User> users = userDao.findAll();
        log.info("application.getAllUser.end");
        return new SuccessDataResult<>(users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList()), "All user list");
    }

    @Transactional
    public Result deleteUserByPhoneNumber(String phoneNumber) {
        User user = userDao.findUserByPhoneNumber(phoneNumber);
        Role role = (Role) user.getRoles();
        log.info("application.deleteUserByPhoneNumber.start");
        if (userDao.existsByPhoneNumber(phoneNumber)) {
            roleDao.delete(role);
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
        return new ErrorDataResult<>(null, "User does not exists");
    }

    @Override
    public DataResult<Integer> findIdByPhoneNumber(String phoneNumber) {
        Integer id = userDao.findIdByPhoneNumber(phoneNumber);
        if(null != id){
            return new SuccessDataResult<>(id, "Finding of id is successfully");
        }else return new ErrorDataResult<>(null, "Id is not exist");
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