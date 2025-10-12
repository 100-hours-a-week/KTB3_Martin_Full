package com.example._th_assignment.repository;

import com.example._th_assignment.dto.CommentDto;
import com.example._th_assignment.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CommentRepository {
    private Long sequence;
    private final HashMap<Long, HashMap<Long, CommentDto>> commentStore;

    public CommentRepository() {
        commentStore = new HashMap<>();
        sequence = 0L;
    }

    public CommentDto save(Long postId, CommentDto comment) {
        comment.setId(++sequence);
        commentStore.computeIfAbsent(postId, k -> new HashMap<>())
                .put(sequence, comment);

        return comment;
    }


    public List<CommentDto> getbyPostId(long postId) {
        HashMap<Long, CommentDto> comments = commentStore.
                computeIfAbsent(postId,  v -> new HashMap<>());
        return new ArrayList<>(comments.values());

    }

    public Optional<CommentDto> getbyCommentId(long postId, long commentId) {
        HashMap<Long, CommentDto> comments = commentStore.get(postId);

        if (comments == null) {
            return Optional.empty();
        }

        CommentDto comment = comments.get(commentId);
        return Optional.ofNullable(comment).filter(c ->!comment.getIsdeleted());
    }



    public void delete(Long postId, Long commentId) {
        HashMap<Long, CommentDto> comments = commentStore.get(postId);
        if (comments != null) {
            comments.get(commentId).setIsdeleted(true);
        }
    }

    public CommentDto update(CommentDto commentDto) {
        HashMap<Long, CommentDto> comments = commentStore.get(commentDto.getPostID());
        comments.replace(commentDto.getId(),  commentDto);
        return comments.get(commentDto.getId());
    }




}
