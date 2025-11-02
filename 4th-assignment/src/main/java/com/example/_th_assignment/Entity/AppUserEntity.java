package com.example._th_assignment.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_users")
public class AppUserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nickname;
    private String email;
    private String password;

    private String image = "";

    @CreationTimestamp
    private LocalDateTime created_at;

    public AppUserEntity() {
    }



    public AppUserEntity(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public AppUserEntity(String nickname, String email, String password, String image) {
    }


    public String getImage() {
        return image;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
