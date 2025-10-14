package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank(message= "nickname should not empty" , groups = {ValidationGroup.Register.class})
    private String nickname;
    @NotBlank(message= "nickname should not empty" , groups = {ValidationGroup.Register.class})
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message= "username should not empty" ,
            groups = {ValidationGroup.Login.class, ValidationGroup.Register.class})
    private String username;
    @NotBlank(message= "nickname should not empty" ,
            groups = {ValidationGroup.Login.class, ValidationGroup.Register.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public UserDto() {
    }

//    public UserDto(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public UserDto(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
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
