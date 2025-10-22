package com.example._th_assignment.Service;

import com.example._th_assignment.CustomException.UserConflictException;
import com.example._th_assignment.CustomException.UserUnAuthorizedException;
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

    public UserDto getbyEmail(String email) {
        return userRepository.getbyEmail(email)
                .orElseThrow(() -> new UserUnAuthorizedException(email));
    }
    public UserDto saveUser (UserDto userDto){
        if(userRepository.getbyEmail(userDto.getEmail()).isPresent()){
            throw new UserConflictException(userDto.getEmail());
        }
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
            throw new UserUnAuthorizedException(username);
        return user;
    }

    public void deleteUser(UserDto user){
        userRepository.delete(user);
    }
    public UserDto apply2User(RequestUserDto requestUserDto) {
        return new UserDto(requestUserDto);
    }

    public UserDto apply2UserForUpdate(RequestUserDto requestUserDto, UserDto user){
        String nickname = requestUserDto.getNickname();
        String email = user.getEmail();
        String password = user.getPassword();
        String image = requestUserDto.getImage();
        return new UserDto(nickname, email, password, image);
    }

    public UserDto apply2UserForPassword(RequestUserDto requestUserDto, UserDto user){
        String nickname = user.getNickname();
        String email = user.getEmail();
        String password = requestUserDto.getPassword();
        String image = user.getImage();

        return new UserDto(nickname, email, password, image);
    }
}
