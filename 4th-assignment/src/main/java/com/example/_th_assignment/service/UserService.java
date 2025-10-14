package com.example._th_assignment.service;

import com.example._th_assignment.dto.UserDto;
import com.example._th_assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getbyName(String name) {
        return userRepository.getbyName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }
    public UserDto saveUser (UserDto userDto){
        if(userDto.getUsername() == null||userDto.getUsername().equals("")||
                userDto.getPassword() == null || userDto.getPassword().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(userDto);
    }

    public UserDto updateUser(String username, UserDto userDto) {
        return userRepository.update(username, userDto);
    }

    public UserDto checkUser(String username, String password) {
        UserDto user = getbyName(username);
        if(!user.getPassword().equals(password)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return user;
    }
}
