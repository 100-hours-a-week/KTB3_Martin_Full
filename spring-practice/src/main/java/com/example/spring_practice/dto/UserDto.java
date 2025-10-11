// UserDto.java
package com.example.spring_practice.dto;

public class UserDto {
    private String username;
    private String email;

    // Getter와 Setter가 반드시 필요합니다.
    // Spring이 JSON 데이터를 이 객체로 변환할 때 사용합니다.
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // sysout으로 확인할 때 객체 정보를 보기 좋게 출력하기 위해 toString()을 오버라이드합니다.
    @Override
    public String toString() {
        return "UserDto{username='" + username + "', email='" + email + "'}";

    }
}
