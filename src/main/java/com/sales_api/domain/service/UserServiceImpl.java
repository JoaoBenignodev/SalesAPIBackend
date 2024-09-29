package com.sales_api.domain.service;

import com.sales_api.Infrastructure.repository.UserRepository;
import com.sales_api.domain.dtos.request.UserRequestActiveDto;
import com.sales_api.domain.dtos.request.UserRequestDto;
import com.sales_api.domain.dtos.response.UserResponseDto;
import com.sales_api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // POST method implementation
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = new User();

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setDocument(userRequestDto.getDocument());
        user.setIsActive(userRequestDto.getIs_active());

        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setDocument(savedUser.getDocument());
        userResponseDto.setIs_active(savedUser.getIsActive());

        return userResponseDto;
    }

    // GET method implementation
    @Override
    public UserResponseDto getUser(Long id) {
        // Looks for an User based on the given "id"
        User existingUser = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found!\n" +
                "The given id:" + id + ", is not related to an existing User!"));

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(existingUser.getId());
        userResponseDto.setName(existingUser.getName());
        userResponseDto.setEmail(existingUser.getEmail());
        userResponseDto.setPassword(existingUser.getPassword());
        userResponseDto.setDocument(existingUser.getDocument());
        userResponseDto.setIs_active(existingUser.getIsActive());

        return userResponseDto;
    }

    // PUT method implementation
    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        // Looks for an User based on the given "id"
        User existingUser = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found!\n" +
                "The given id:" + id + ", is not related to an existing User!"));

        existingUser.setName(userRequestDto.getName());
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setPassword(userRequestDto.getPassword());
        existingUser.setDocument(userRequestDto.getDocument());
        existingUser.setIsActive(userRequestDto.getIs_active());

        userRepository.save(existingUser);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(existingUser.getId());
        userResponseDto.setName(existingUser.getName());
        userResponseDto.setEmail(existingUser.getEmail());
        userResponseDto.setPassword(existingUser.getPassword());
        userResponseDto.setDocument(existingUser.getDocument());
        userResponseDto.setIs_active(existingUser.getIsActive());

        return userResponseDto;
    }

    // GET method implementation (for status only)
    @Override
    public UserResponseDto updateUserStatus(Long id, UserRequestActiveDto userRequestActiveDto) {
        // Looks for an User based on the given "id"
        User existingUser = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found!\n" +
                "The given id:" + id + ", is not related to an existing User!"));

        existingUser.setIsActive(userRequestActiveDto.getIs_active());

        userRepository.save(existingUser);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(existingUser.getId());
        userResponseDto.setName(existingUser.getName());
        userResponseDto.setEmail(existingUser.getEmail());
        userResponseDto.setPassword(existingUser.getPassword());
        userResponseDto.setDocument(existingUser.getDocument());
        userResponseDto.setIs_active(existingUser.getIsActive());

        return userResponseDto;
    }

    // DELETE method implementation
    @Override
    public UserResponseDto deleteUser(Long id) {
        // Looks for an User based on the given "id"
        User existingUser = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found!\n" +
                "The given id:" + id + ", is not related to an existing User!"));

        userRepository.delete(existingUser);

        return null;
    }

}
