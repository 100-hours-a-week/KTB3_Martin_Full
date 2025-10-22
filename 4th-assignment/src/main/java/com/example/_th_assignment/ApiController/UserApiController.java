package com.example._th_assignment.ApiController;

import com.example._th_assignment.ApiResponse.ApiResponse;
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
    public ResponseEntity<Object> login(
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


        return ResponseEntity.
                status(HttpStatus.OK)
                .body(ApiResponse.success("login success", user));
    }

    @DeleteMapping("/sesion")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        HttpSession session = sessionManager.access2Auth(request);
        session.invalidate();

        return ResponseEntity.
                status(HttpStatus.OK).
                body(ApiResponse.success("logout success"));
    }

    @PostMapping
    public ResponseEntity<Object> register(
            @Validated(ValidationGroup.Register.class) @RequestBody RequestUserDto newuser){
        checkValidPassword(newuser);
        UserDto user = userService.apply2User(newuser);
        user = userService.saveUser(user);
        return ResponseEntity.ok(ApiResponse.success("register success", user));

    }

    @GetMapping
    public ResponseEntity<Object> getUserProperty(HttpServletRequest request) {
        HttpSession session = sessionManager.access2Auth(request);
        UserDto user = (UserDto) session.getAttribute("user");

        return ResponseEntity.ok(ApiResponse.success("user found", user));

    }
    @PutMapping
    public ResponseEntity<Object> updateUser(
            @Validated(ValidationGroup.UpdateProperty.class ) @RequestBody RequestUserDto newuser,
            HttpServletRequest request) {
        HttpSession session = sessionManager.access2Auth(request);
        UserDto user = (UserDto) session.getAttribute("user");

        user = userService.apply2UserForUpdate(newuser, user);
        user = userService.updateUser(user.getEmail(),user);
        session.removeAttribute("user");
        session.setAttribute("user", user);

        return ResponseEntity.ok(ApiResponse.success("update success", user));

    }

    @PutMapping("/password")
    public ResponseEntity<?> updateUserPassword(
            @Validated(ValidationGroup.UpdatePassword.class) @RequestBody RequestUserDto newuser,
            HttpServletRequest request
    ){

        HttpSession session = sessionManager.access2Auth(request);
        checkValidPassword(newuser);
        UserDto user = (UserDto) session.getAttribute("user");


        user = userService.apply2UserForPassword(newuser, user);
        user = userService.updateUserPassword(user.getEmail(),user);
        session.removeAttribute("user");
        session.setAttribute("user", newuser);

        return ResponseEntity.ok().body(ApiResponse.success("password updated", newuser));

    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteUser(HttpServletRequest request) {
        HttpSession session = sessionManager.access2Auth(request);

        UserDto user = (UserDto) session.getAttribute("user");
        userService.deleteUser(user);
        session.removeAttribute("user");
        session.invalidate();
        return ResponseEntity.noContent().build();

    }

    public void checkValidPassword(RequestUserDto user){
        if(!user.getPassword().equals(user.getCheckingpassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and checkingpassword are not same");
    }


}
