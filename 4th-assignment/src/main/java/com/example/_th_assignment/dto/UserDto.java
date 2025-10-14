package com.example._th_assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {
    private String nickname;
    private String email;

    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;


    public UserDto() {


    }
    public UserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
