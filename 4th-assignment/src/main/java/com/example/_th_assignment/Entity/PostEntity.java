package com.example._th_assignment.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUserEntity user;

    private String title;
    private String content;
    private String post_image_url;

    @CreationTimestamp
    private LocalDateTime created_at;

    private boolean is_deleted = false;

    public long getId() {
        return id;
    }

    public AppUserEntity getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPost_image_url() {
        return post_image_url;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setUser(AppUserEntity user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPost_image_url(String post_image_url) {
        this.post_image_url = post_image_url;
    }


}
