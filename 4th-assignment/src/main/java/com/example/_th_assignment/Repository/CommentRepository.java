package com.example._th_assignment.Repository;

import com.example._th_assignment.Dto.CommentDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CommentRepository {
    private Long sequence;
    private final HashMap<Long, LinkedHashMap<Long, CommentDto>> commentStore;

    public CommentRepository() {
        commentStore = new HashMap<>();
        sequence = 0L;

        save(1L, new CommentDto(1L, "good"));
        save(2L, new CommentDto(2L, "for"));
        save(2L, new CommentDto(2L, "you"));
    }

    public CommentDto save(Long postId, CommentDto comment) {
        comment.setId(++sequence);
        comment.setPostId(postId);
        commentStore.computeIfAbsent(postId, k -> new LinkedHashMap<>())
                .put(sequence, comment);

        return comment;
    }


    public List<CommentDto> getbyPostId(long postId) {
        HashMap<Long, CommentDto> comments = commentStore.
                computeIfAbsent(postId,  v -> new LinkedHashMap<>());
        ArrayList<CommentDto> commentview = new ArrayList<>();

        for(CommentDto comment : comments.values()) {
            if(!comment.getIsdeleted()) commentview.add(comment);
        }

        return commentview;

    }

    public Optional<CommentDto> getbyPostIdAndCommentId(long postId, long commentId) {
        LinkedHashMap<Long, CommentDto> comments = commentStore.get(postId);

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

    public CommentDto update(Long postId, Long commentId, CommentDto commentDto) {
        HashMap<Long, CommentDto> comments = commentStore.get(postId);
        comments.replace(commentId,  commentDto);
        return comments.get(commentDto.getId());
    }




}
