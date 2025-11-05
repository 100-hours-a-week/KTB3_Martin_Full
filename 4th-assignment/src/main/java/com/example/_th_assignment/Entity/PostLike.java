package com.example._th_assignment.Entity;

import com.example._th_assignment.Dto.LikeDto;
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



    public PostLike() {}

    public PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    public static PostLike from(Post post, User user) {
        return new PostLike(post, user);
    }

    public LikeDto toDto() {
        long id = this.id;
        long postid = this.post.getId();
        String authorEmail = this.user.getEmail();

        return new LikeDto(id,postid,authorEmail);
    }




}
