package com.example._th_assignment.JpaRepository;

import com.example._th_assignment.Entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeJpaRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findAllByUser_IdAndIsdeletedFalse(long userId);
    List<PostLike> findAllByPost_IdAndIsdeletedFalse(long postId);

    long countAllByPost_IdAndIsdeletedFalse(long postId);

}
