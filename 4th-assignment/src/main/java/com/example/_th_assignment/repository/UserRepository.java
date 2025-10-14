package com.example._th_assignment.repository;

import com.example._th_assignment.dto.PostDto;
import com.example._th_assignment.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class UserRepository {
    private final HashMap<String, UserDto> userStore;

    public UserRepository() {
        userStore = new HashMap<>();
    }

    public Optional<UserDto> getbyName(String username) {
        return Optional.ofNullable(userStore.get(username));
    }

    public UserDto save(UserDto userDto) {

        userStore.put(userDto.getUsername(), userDto);
        return userDto;
    }
    public UserDto update(String username, UserDto user) {
        userStore.replace(username, user);
        return userStore.get(username);
    }

    public void delete(String username) {
        UserDto userDto = getbyName(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userStore.remove(username);

    }


}
