package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank(message= "nickname should not empty" ,
            groups = {ValidationGroup.Register.class, ValidationGroup.UpdateProperty.class})
    private String nickname;

    @Email(message = "malformed email")
    @NotBlank(message= "nickname should not empty" ,
            groups = {ValidationGroup.Register.class, ValidationGroup.UpdateProperty.class})
    private String email;

    @NotBlank(message= "username should not empty" ,
            groups = {ValidationGroup.Login.class, ValidationGroup.Register.class})
    private  String username;
    @NotBlank(message= "passwd should not empty" ,
            groups = {ValidationGroup.Login.class, ValidationGroup.Register.class, ValidationGroup.UpdatePassword.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public UserDto() {
    }


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
