package com.example._th_assignment.Service;

import com.example._th_assignment.CustomException.CommentNotFoundException;
import com.example._th_assignment.Dto.CommentDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {


    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(PostService postService, CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getByPostId(Long postId) {
        return commentRepository.getbyPostId(postId);
    }

    public CommentDto getByPostIdAndCommentId(Long postId, Long commentId) {
        return commentRepository.getbyPostIdAndCommentId(postId, commentId)
                .orElseThrow(() -> new CommentNotFoundException(postId, commentId));
    }

    public CommentDto saveComment(Long postId, CommentDto commentDto) {

        return commentRepository.save(postId, commentDto);
    }

    public CommentDto updateComment(Long postId, Long commentId, CommentDto comment) {
        CommentDto oldComment = getByPostIdAndCommentId(postId, commentId);

        String content = comment.getContent();
        String authorEmail = oldComment.getAuthorEmail();
        String author = oldComment.getAuthor();
        String birthtime = oldComment.getBirthTime();

        CommentDto newcomment = new CommentDto(commentId, postId, author, authorEmail, content, birthtime);

        return commentRepository.update(postId, commentId, newcomment);
    }

    public void deleteComment(Long postId, Long commentId) {
        getByPostIdAndCommentId(postId, commentId);
        commentRepository.delete(postId, commentId);
    }

    public void deleteAllComment(Long postId) {
        getByPostId(postId);
        commentRepository.delete(postId);
    }

    public long countByPostId(Long postId) {
        return commentRepository.count(postId);
    }

    public CommentDto apply2Comment(CommentDto requestcomment, UserDto user){
        long id = requestcomment.getId();
        long postid = requestcomment.getPostid();
        String content = requestcomment.getContent();
        String authorEmail = user.getEmail();
        String author = user.getNickname();
        String birthtime = requestcomment.getBirthTime();

        return new CommentDto(id,postid,author,authorEmail,content,birthtime);
    }


}
