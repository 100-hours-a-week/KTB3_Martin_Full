package com.example._th_assignment.JpaRepository;

import com.example._th_assignment.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndIsdeletedFalse(Long id);
    List<Comment> findAllByUser_IdAndIsdeletedFalse(Long userid);
    List<Comment> findAllByPost_IdAndIsdeletedFalse(Long postid);


}
