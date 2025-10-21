package com.example._th_assignment.Repository;

import com.example._th_assignment.Dto.CommentDto;
import com.example._th_assignment.Dto.LikeDto;
import com.example._th_assignment.Dto.LikeDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LikeRepository {
    private final ConcurrentHashMap<Long, AtomicLong> sequencemap;
    private final HashMap<Long, LinkedHashMap<String, LikeDto>> likeStore;

    public LikeRepository() {
        this.likeStore = new LinkedHashMap<>();
        this.sequencemap = new ConcurrentHashMap<>();

        save(1L, new LikeDto(1L, "foo@bar"));
        save(2L, new LikeDto(2L, "foo@bar"));
        save(3L, new LikeDto(3L, "foo@bar"));
    }

    public List<LikeDto> getbyPostId(long postId) {
        LinkedHashMap<String, LikeDto> likeMap = likeStore.
                computeIfAbsent(postId, v -> new LinkedHashMap<>());
        ArrayList<LikeDto> likes = new ArrayList<>();
        for(LikeDto like: likeMap.values()){
            if(!like.getIsdeleted()) likes.add(like);
        }
        return likes;
    }

    public Optional<LikeDto> getbyPostIdAndAuthorEmail(long postId, String author){
        LinkedHashMap<String, LikeDto> likeMap = likeStore.get(postId);
        if(likeMap == null) return Optional.empty();

        return Optional.ofNullable(likeMap.get(author)).filter( l -> !l.getIsdeleted());
    }

    public LikeDto save(Long postId, LikeDto like){
        long sequence = sequencemap.computeIfAbsent(postId, v -> new AtomicLong())
                .incrementAndGet();
        Map<String, LikeDto> map = likeStore.computeIfAbsent(postId, v -> new LinkedHashMap<>());
        if(map.containsKey(like.getAuthorEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "1 post, 1 user, 1 like");
        }
        like.setPostid(postId);
        like.setId(sequence);
        map.put(like.getAuthorEmail(), like);
        return like;
    }

    public LikeDto update(Long postId, String authorEmail, LikeDto newlike) {
        getbyPostIdAndAuthorEmail(postId, authorEmail).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like not found"));
        Map<String, LikeDto> likeMap = likeStore.get(postId);
        likeMap.replace(authorEmail, newlike);
        return likeMap.get(authorEmail);
    }
    public void delete(Long postId, String authorEmail){
        LikeDto like = getbyPostIdAndAuthorEmail(postId, authorEmail).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like not found"));
        like.setIsdeleted(true);
    }
    public void delete(Long postId){
        if(!likeStore.containsKey(postId)) return;
        Map<String, LikeDto> likeMap = likeStore.get(postId);
        for(LikeDto like: likeMap.values()){
            like.setIsdeleted(true);
        }
    }

    public long count(Long postId){
        return sequencemap.get(postId).get();
    }
}
