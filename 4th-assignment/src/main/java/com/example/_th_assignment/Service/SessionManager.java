package com.example._th_assignment.Service;

import com.example._th_assignment.Dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SessionManager {
    public SessionManager() {
    }

    public HttpSession access2Auth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Session is null");
        }
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "need to log in");
        }
        return session;
    }
    public HttpSession access2Resource(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unlogged in");
        }
        return session;
    }
}
