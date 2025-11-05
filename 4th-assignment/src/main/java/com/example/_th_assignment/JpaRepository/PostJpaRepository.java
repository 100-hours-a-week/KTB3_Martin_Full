package com.example._th_assignment.JpaRepository;

import com.example._th_assignment.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByidAndIsdeletedFalse(long postId);
    List<Post> findAllByUser_IdAndIsdeletedFalse(Long id);
    List<Post> findAllByIsdeletedFalse();
    Long countAllByIsdeletedFalse();
    Boolean existsByidAndIsdeletedFalse(Long id);


    Optional<Post> findByUser_IdAndIsdeletedFalse(long userid);
}
