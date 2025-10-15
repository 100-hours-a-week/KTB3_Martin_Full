package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    private String nickname;


    @NotBlank(message= "nickname should not empty" ,
            groups = {ValidationGroup.Login.class})
    private String email;

    @NotBlank(message= "passwd should not empty" ,
            groups = {ValidationGroup.Login.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String image = "";

    public UserDto() {

    }



    public UserDto(UserRequestDto registerUser) {
        this.nickname = registerUser.getNickname();
        this.email = registerUser.getEmail();
        this.password = registerUser.getPassword();
        if(registerUser.getImage()!=null) {
            this.image = registerUser.getImage();
        }
    }


    public UserDto( String password, String email, String nickname) {

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


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }



}
