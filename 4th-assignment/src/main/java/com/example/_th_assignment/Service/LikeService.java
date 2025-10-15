package com.example._th_assignment.Service;

import com.example._th_assignment.Dto.LikeDto;
import com.example._th_assignment.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<LikeDto> getbyPostId(Long postId){
        return likeRepository.getbyPostId(postId);
    }
    public LikeDto getbyPostIdAndAuthor(Long postId, String author){
        LikeDto like= likeRepository.getbyPostIdAndAuthor(postId, author).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like not found"));

        return like;
    }

    public LikeDto saveLike(Long postid, LikeDto likeDto){
        return likeRepository.save(postid, likeDto);
    }

    public LikeDto updateLike(Long postid, String author, LikeDto likeDto){
        return likeRepository.update(postid, author, likeDto);
    }

    public void deleteLike(Long postid, String author){
        likeRepository.delete(postid, author);
    }

    public void deleteAllLike(Long postid){
        likeRepository.delete(postid);
    }





}
