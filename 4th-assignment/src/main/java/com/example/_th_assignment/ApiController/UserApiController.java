package com.example._th_assignment.ApiController;

import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Dto.ValidationGroup;
import com.example._th_assignment.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    public UserApiController (UserService userService) {
        this.userService = userService;
    }
    @PostMapping("auth/login-session")
    public ResponseEntity<?> login(@Validated(ValidationGroup.Login.class) @RequestBody UserDto tryuser,
                                   HttpServletRequest request) {

        UserDto user = userService.checkUser(tryuser.getUsername(), tryuser.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("message", "login success");
        map.put("user", user);

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(map);
    }

    @DeleteMapping("auth/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return ResponseEntity.ok(Map.of("message", "logout success"));
    }

}
