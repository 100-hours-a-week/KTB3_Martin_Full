package com.example._th_assignment.JpaRepository;

import com.example._th_assignment.Entity.Comment;
import com.example._th_assignment.Entity.Post;
import com.example._th_assignment.Entity.PostLike;
import com.example._th_assignment.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class InitRunner implements CommandLineRunner {

    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository appUserJpaRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final PostLikeJpaRepository postLikeJpaRepository;


    public InitRunner(PostJpaRepository postJpaRepository,
                      UserJpaRepository appUserJpaRepository,
                      CommentJpaRepository commentJpaRepository,
                      PostLikeJpaRepository postLikeJpaRepository) {
        this.postJpaRepository = postJpaRepository;
        this.appUserJpaRepository = appUserJpaRepository;
        this.commentJpaRepository = commentJpaRepository;
        this.postLikeJpaRepository = postLikeJpaRepository;

    }

    @Override
    @Transactional
    public void run(String... args) {
        // 애플리케이션 완전 구동 후 실행됨 ✅
        User user = new User("martin", "foo@bar", "1234", "_");
        appUserJpaRepository.save(user);
        user = appUserJpaRepository.findByEmailAndIsdeletedFalse("foo@bar").orElse(null);
        Post post1 = new Post("Hello", "World", user, "");
        Post post2 = new Post("Spring Boot", "Runner Test", user, "");
        postJpaRepository.save(post1);
        postJpaRepository.save(post2);
        List<Post> posts = postJpaRepository.findAllByUser_IdAndIsdeletedFalse(user.getId());
        Post post = posts.get(0);
        Comment comment = new Comment("this is comment", user, post);
        commentJpaRepository.save(comment);
        comment = commentJpaRepository.findByIdAndIsdeletedFalse(1L).orElse(null);

        PostLike postLike = new PostLike(post, user);
        postLikeJpaRepository.save(postLike);


        user.delete();


        user = appUserJpaRepository.findByEmailAndIsdeletedFalse("foo@bar").orElse(null);
        if(user == null) {System.out.println("success");}



        System.out.println(post.getTitle() + " " + post.getContent() + " " + post.getUser().getNickname());
        System.out.println(comment.getContent());
        System.out.println("✅ 초기 데이터 삽입 완료");
    }
}

