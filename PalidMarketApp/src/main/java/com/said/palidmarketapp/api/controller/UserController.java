package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.UserService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserLoginDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController  {
    private final UserService userService;

   // @PreAuthorize("hasRole(ADMIN)")
    @GetMapping("/admin/getAll")
    public ResponseEntity<DataResult<List<UserDto>>> getAll(){
        try {
            DataResult<List<UserDto>> resultUser = userService.getAll();
            if (resultUser.isSuccess()) {
                return ResponseEntity.ok(resultUser);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultUser);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/user/{phoneNumber}")
    public ResponseEntity<Result> deleteUser(@PathVariable String phoneNumber) {
        try {
            Result result = userService.deleteUserByPhoneNumber(phoneNumber);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity<Result> updateUser(@RequestBody UserSaveDto newUser) {
        try {
            Result result = userService.updateUser(newUser);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PatchMapping("/user/{phoneNumber}/{firstName}")
    public ResponseEntity<Result> updateFirstName(@PathVariable String phoneNumber, @PathVariable String firstName){
        try {
            Result result = userService.updateFirstName(phoneNumber, firstName);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/user/{phoneNumber}/{lastName}")
    public ResponseEntity<Result> updateLastName(@PathVariable String phoneNumber, @PathVariable String lastName){
        try {
            Result result = userService.updateLastName(phoneNumber, lastName);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user/{phoneNumber}")
    public DataResult<Integer> findIdByPhoneNumber(@PathVariable String phoneNumber){
        return userService.findIdByPhoneNumber(phoneNumber);
    }
}
