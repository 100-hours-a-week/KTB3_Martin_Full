package com.example._th_assignment.service;

import com.example._th_assignment.dto.CommentDto;
import com.example._th_assignment.repository.CommentRepository;
import com.example._th_assignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentRepository commentRepository;

    public CommentService(PostService postService, CommentRepository commentRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getByPostId(Long postId) {
        return commentRepository.getbyPostId(postId);
    }

    public CommentDto getByPostIdAndCommentId(Long postId, Long commentId) {
        return commentRepository.getbyPostIdAndCommentId(postId, commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public CommentDto saveComment(Long postId, CommentDto commentDto) {

        return commentRepository.save(postId, commentDto);
    }

    public void deleteComment(Long postId, Long commentId) {
        commentRepository.delete(postId, commentId);
    }

    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        return commentRepository.update(postId, commentId, commentDto);
    }

}
