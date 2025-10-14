package com.example._th_assignment.Repository;

import com.example._th_assignment.Dto.UserDto;
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
        UserDto user = new UserDto("myid", "mypassword", "foo@bar", "dummy");
        save(user);
    }

    public Optional<UserDto> getbyName(String username) {
        return Optional.ofNullable(userStore.get(username));
    }

    public UserDto save(UserDto userDto) {
        if(userStore.containsKey(userDto.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "same username already exists");

        userStore.put(userDto.getUsername(), userDto);
        return userDto;
    }
    public UserDto update(String username, UserDto user) {
        userStore.replace(username, user);
        return userStore.get(username);
    }

    public void delete(UserDto user) {
        String username = user.getUsername();
        getbyName(username).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userStore.remove(username);

    }


}
