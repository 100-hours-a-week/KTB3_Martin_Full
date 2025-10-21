package com.example._th_assignment.Repository;

import com.example._th_assignment.Dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CommentRepository {
//    private Long sequence;
    private final ConcurrentHashMap<Long, AtomicLong> sequencemap;
    private final ConcurrentHashMap<Long, LinkedHashMap<Long, CommentDto>> commentStore;

    public CommentRepository() {
        commentStore = new ConcurrentHashMap<>();
        sequencemap = new ConcurrentHashMap<>();


        save(1L, new CommentDto(1L, "good", "foo@bar"));
        save(2L, new CommentDto(2L, "for", "foo@bar"));
        save(2L, new CommentDto(2L, "you", "foo@bar"));
    }

    public CommentDto save(Long postId, CommentDto comment) {
        long sequence = sequencemap.computeIfAbsent(postId, v -> new AtomicLong())
                .incrementAndGet();

        comment.setId(sequence);
        comment.setPostId(postId);
        String timeStamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss"));
        comment.setBirthTime(timeStamp);
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
        return Optional.ofNullable(comments.get(commentId)).filter(c ->!c.getIsdeleted());
    }


    public CommentDto update(Long postId, Long commentId, CommentDto newcomment) {
        getbyPostIdAndCommentId(postId,commentId).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment not found"));
        LinkedHashMap<Long, CommentDto> comments = commentStore.get(postId);
        comments.replace(commentId,  newcomment);
        return comments.get(newcomment.getId());
    }


    public void delete(Long postId, Long commentId) {
        CommentDto comment = getbyPostIdAndCommentId(postId, commentId).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment not found"));
        comment.setIsdeleted(true);

    }

    public void delete(Long postId) {
        if(!commentStore.containsKey(postId)) return;
        LinkedHashMap<Long, CommentDto> comments = commentStore.get(postId);
        for(CommentDto comment : comments.values()) {
            comment.setIsdeleted(true);
        }
    }

    public long count(long postid) {
        return sequencemap.get(postid).get();
    }




}
