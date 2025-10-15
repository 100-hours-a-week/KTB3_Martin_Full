package com.example._th_assignment.Service;

import com.example._th_assignment.Dto.RequestUserDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getbyEmail(String name) {
        return userRepository.getbyEmail(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "wrong id or password"));
    }
    public UserDto saveUser (UserDto userDto){
        return userRepository.save(userDto);
    }

    public UserDto updateUser(String useremail, UserDto newuser) {
        return userRepository.update(useremail, newuser);
    }

    public UserDto updateUserPassword(String useremail, UserDto newuser) {
        return userRepository.updatePassword(useremail, newuser);
    }


    public UserDto checkUser(String username, String password) {
        UserDto user = getbyEmail(username);
        if(!user.getPassword().equals(password))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "wrong id or password");
        return user;
    }

    public void deleteUser(UserDto user){
        userRepository.delete(user);
    }
    public UserDto apply2UserDto(RequestUserDto requestUserDto) {
        return new UserDto(requestUserDto);
    }
}
