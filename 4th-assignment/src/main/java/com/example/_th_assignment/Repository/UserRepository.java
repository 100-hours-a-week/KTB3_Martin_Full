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
        UserDto user = new UserDto("Mypassword1!", "foo@bar", "dummy");
        save(user);
    }

    public Optional<UserDto> getbyEmail(String useremail) {
        return Optional.ofNullable(userStore.get(useremail));
    }

    public UserDto save(UserDto userDto) {
        if(userStore.containsKey(userDto.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "same email already exists");

        userStore.put(userDto.getEmail(), userDto);
        return userDto;
    }
    public UserDto update(String email, UserDto updateuser) {
        UserDto user =getbyEmail(email).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setNickname(updateuser.getNickname());
        user.setImage(updateuser.getImage());

        userStore.replace(email, user);
        return userStore.get(email);
    }

    public UserDto updatePassword(String email, UserDto newuser) {
        UserDto user =getbyEmail(email).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setPassword(newuser.getPassword());

        userStore.replace(email, user);
        return userStore.get(email);
    }

    public void delete(UserDto user) {
        String email = user.getEmail();
        getbyEmail(email).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userStore.remove(email);

    }


}
