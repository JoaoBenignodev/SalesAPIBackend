package com.sales_api.domain.service;

import com.sales_api.domain.dtos.request.UserRequestActiveDto;
import com.sales_api.domain.dtos.request.UserRequestDto;
import com.sales_api.domain.dtos.response.UserResponseDto;

public interface UserServiceInterface {

    UserResponseDto save(UserRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    UserResponseDto updateUserStatus(Long id, UserRequestActiveDto userRequestActiveDto);
    UserResponseDto deleteUser(Long id);
}
