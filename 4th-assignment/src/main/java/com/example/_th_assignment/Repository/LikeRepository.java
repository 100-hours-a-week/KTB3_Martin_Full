package com.example._th_assignment.Repository;

import com.example._th_assignment.Dto.CommentDto;
import com.example._th_assignment.Dto.LikeDto;
import com.example._th_assignment.Dto.LikeDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Repository
public class LikeRepository {

    private Long sequence = 0L;
    private final HashMap<Long, LinkedHashMap<String, LikeDto>> likeStore;

    public LikeRepository() {
        this.likeStore = new LinkedHashMap<>();

        save(1L, new LikeDto(1L, "Hiccup"));
        save(2L, new LikeDto(2L, "Stoick"));
        save(2L, new LikeDto(2L, "Valka"));
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

    public Optional<LikeDto> getbyPostIdAndAuthor(long postId, String author){
        LinkedHashMap<String, LikeDto> likeMap = likeStore.get(postId);
        if(likeMap == null) return Optional.empty();

        return Optional.ofNullable(likeMap.get(author)).filter( l -> !l.getIsdeleted());
    }

    public LikeDto save(Long postId, LikeDto like){
        like.setId(++sequence);
        like.setPostid(postId);
        likeStore.computeIfAbsent(postId, v -> new LinkedHashMap<>()).put(like.getAuthor(), like);
        return like;
    }

    public LikeDto update(Long postId, String author, LikeDto newlike) {
        getbyPostIdAndAuthor(postId, author).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like not found"));
        Map<String, LikeDto> likeMap = likeStore.get(postId);
        likeMap.replace(author, newlike);
        return likeMap.get(author);
    }

    public void delete(Long postId, String author){
        LikeDto like = getbyPostIdAndAuthor(postId, author).
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
}
