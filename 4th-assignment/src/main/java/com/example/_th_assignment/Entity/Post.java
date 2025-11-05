package com.example._th_assignment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(nullable = false, length = 26)
    private String title;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private long viewcount = 0;

    private String imageurl;

    @CreationTimestamp
    private LocalDateTime createdat;

    @Column(nullable = false)
    private boolean isdeleted = false;

    public Post() {}

    public Post(String title, String content, User user, String imageurl) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.imageurl = imageurl;
    }

    public void plusViewCount(){
        this.viewcount++;
    }

    public void delete(){
        this.isdeleted=true;
    }




}
