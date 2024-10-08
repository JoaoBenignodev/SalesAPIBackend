package com.sales_api.controller;

import com.sales_api.domain.dtos.request.UserRequestActiveDto;
import com.sales_api.domain.dtos.request.UserRequestDto;
import com.sales_api.domain.dtos.response.SaleResponseDto;
import com.sales_api.domain.dtos.response.UserResponseDto;
import com.sales_api.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/add/") // http://localhost:8080/api/users/add/
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
        return userService.save(userRequestDto);
    }

    @GetMapping("/{id}/") // http://localhost:8080/api/users/x/
    public UserResponseDto getUser(
            @PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping // http://localhost:8080/api/users/
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }


    @PutMapping("/{id}/change/") // http://localhost:8080/api/users/x/change/
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.updateUser(id, userRequestDto);
    }

    @PutMapping("/{id}/changestatus/") // http://localhost:8080/api/users/x/changestatus/
    public UserResponseDto updateUserStatus(
            @PathVariable Long id,
            @RequestBody UserRequestActiveDto userRequestActiveDto
    ) {
        return userService.updateUserStatus(id, userRequestActiveDto);
    }

    @DeleteMapping("/{id}/delete/") // http://localhost:8080/api/users/x/delete/
    public UserResponseDto deleteUser(
            @PathVariable Long id) {
        return userService.deleteUser(id);
    }

}