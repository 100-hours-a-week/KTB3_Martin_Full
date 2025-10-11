// UserApiController.java
package com.example.spring_practice.controller;

import com.example.spring_practice.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody, 주로 API를 만들 때 사용합니다.
public class UserApiController {

    // HTTP POST 요청이 "/api/user" 경로로 올 때 이 메서드를 실행합니다.
    @PostMapping("/api/user")
    // @RequestBody: 요청의 본문(body)에 담긴 JSON 데이터를 UserDto 객체로 변환해줍니다.
    public String processUser(@RequestBody UserDto userDto) {
        // 서버 콘솔에 받은 데이터를 출력합니다.
        System.out.println("Received data: " + userDto.toString());

        // 클라이언트에게 성공 메시지를 반환합니다.
        return "User created successfully: " + userDto.getUsername();
    }
}

