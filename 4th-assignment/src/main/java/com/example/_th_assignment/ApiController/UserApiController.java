package com.example._th_assignment.ApiController;

import com.example._th_assignment.Dto.RequestUserDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Dto.ValidationGroup;
import com.example._th_assignment.Service.SessionManager;
import com.example._th_assignment.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public UserApiController (UserService userService, SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;

    }
    @PostMapping("/session")
    public ResponseEntity<Map<String, Object>> login(
            @Validated(ValidationGroup.Login.class) @RequestBody UserDto tryuser,
                                   HttpServletRequest request) {

        UserDto user = userService.checkUser(tryuser.getEmail(), tryuser.getPassword());
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        session = request.getSession();
        session.setAttribute("user", user);

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("message", "login success");
        map.put("user", user);

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(map);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unlogged in");
        return ResponseEntity.
                status(HttpStatus.OK).
                body((Map.of("message", "logout success")));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> register(
            @Validated(ValidationGroup.Register.class) @RequestBody RequestUserDto updateuser){
        if(!updateuser.getPassword().equals(updateuser.getCheckingpassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and checkingpassword are not same");
        UserDto user = userService.apply2UserDto(updateuser);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("message", "register success");
        map.put("user", userService.saveUser(user));
        return ResponseEntity.
                status(HttpStatus.OK).
                body(map);

    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserProperty(HttpServletRequest request) {
        sessionManager.access2Auth(request);
        HttpSession session = request.getSession(false);
        UserDto user = (UserDto) session.getAttribute("user");

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("message", "user found");
        map.put("user", user);
        return ResponseEntity.
                status(HttpStatus.OK).
                body(map);
    }
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateUser(
            @Validated(ValidationGroup.UpdateProperty.class ) @RequestBody RequestUserDto updateuser,
            HttpServletRequest request) {
        HttpSession session = sessionManager.access2Auth(request);
        UserDto previousUser = (UserDto) session.getAttribute("user");
//        if(user.getEmail() != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email can't be changed");
//        if(user.getPassword() != null)  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password can't be changed in here");

        UserDto newuser = userService.apply2UserDto(updateuser);
        newuser = userService.updateUser(previousUser.getEmail(),newuser);
        session.removeAttribute("user");
        session.setAttribute("user", newuser);


        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("message", "update success");
        map.put("user", newuser);
        return ResponseEntity.
                status(HttpStatus.OK).
                body(map);
    }

    @PutMapping("/password")
    public ResponseEntity<Map<String, Object>> updateUserPassword(
            @Validated(ValidationGroup.UpdatePassword.class) @RequestBody RequestUserDto updateuser,
            HttpServletRequest request
    ){
        if(!updateuser.getPassword().equals(updateuser.getCheckingpassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and checkingpassword are not same");

        sessionManager.access2Auth(request);
        HttpSession session = request.getSession(false);
        UserDto previousUser = (UserDto) session.getAttribute("user");
//        if(user.getEmail() != null)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email can't be changed");
//        if(user.getNickname() != null)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nickname can't be changed in here");

        UserDto newuser = userService.apply2UserDto(updateuser);
        newuser = userService.updateUserPassword(previousUser.getEmail(), newuser);
        session.removeAttribute("user");
        session.setAttribute("user", newuser);

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("message", "password updated");
        map.put("user", newuser);
        return ResponseEntity.
                status(HttpStatus.OK).
                body(map);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteUser(HttpServletRequest request) {
        sessionManager.access2Auth(request);
        HttpSession session = request.getSession(false);

        UserDto user = (UserDto) session.getAttribute("user");
        userService.deleteUser(user);
        session.removeAttribute("user");
        session.invalidate();

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("message", "delete success");

        return ResponseEntity.noContent().build();

    }


}
