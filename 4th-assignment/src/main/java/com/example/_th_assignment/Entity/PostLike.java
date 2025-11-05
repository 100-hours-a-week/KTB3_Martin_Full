package com.example._th_assignment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_likes")
@Getter
@Setter
public class PostLike {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postid")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(nullable = false)
    private boolean isdeleted =false;

    public PostLike() {}

    public PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
    }
    public void delete(){
        this.isdeleted=true;
    }



}
