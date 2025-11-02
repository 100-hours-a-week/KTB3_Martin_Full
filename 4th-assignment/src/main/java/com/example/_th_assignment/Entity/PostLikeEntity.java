package com.example._th_assignment.Entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

public class PostLikeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean is_deleted =false;

    public PostLikeEntity() {}

    public PostLikeEntity(PostEntity post, User user) {
        this.post = post;
        this.user = user;
    }



}
