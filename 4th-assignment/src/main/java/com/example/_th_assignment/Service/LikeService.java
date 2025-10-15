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
    public LikeDto getbyPostIdAndAuthorEmail(Long postId, String authorEmail){
        return likeRepository.getbyPostIdAndAuthorEmail(postId, authorEmail).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like not found"));

    }

    public LikeDto saveLike(Long postid, LikeDto likeDto){
        return likeRepository.save(postid, likeDto);
    }

    public LikeDto updateLike(Long postid, String authorEmail, LikeDto likeDto){
        return likeRepository.update(postid, authorEmail, likeDto);
    }

    public void deleteLike(Long postid, String authorEmailEmail){
        likeRepository.delete(postid, authorEmailEmail);
    }

    public void deleteAllLike(Long postid){
        likeRepository.delete(postid);
    }





}
