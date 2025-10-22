package com.example._th_assignment.Service;

import com.example._th_assignment.CustomException.LikeNotFoundException;
import com.example._th_assignment.Dto.LikeDto;
import com.example._th_assignment.Dto.UserDto;
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
                orElseThrow(()-> new LikeNotFoundException(postId, authorEmail));
    }

    public boolean isExistLike(long postId, String authorEmail){
        return likeRepository.isExist(postId, authorEmail);

    }

    public LikeDto saveLike(Long postid, LikeDto likeDto){
        String email = likeDto.getAuthorEmail();

        if(isExistLike(postid, email)) throw new LikeNotFoundException(postid, email);

        return likeRepository.save(postid, likeDto);
    }

    public LikeDto updateLike(Long postid, String authorEmail, LikeDto likeDto){
        getbyPostIdAndAuthorEmail(postid, authorEmail);
        return likeRepository.update(postid, authorEmail, likeDto);
    }

    public void deleteLike(Long postid, String authorEmail){
        getbyPostIdAndAuthorEmail(postid, authorEmail);
        likeRepository.delete(postid, authorEmail);
    }

    public void deleteAllLike(Long postid){
        getbyPostId(postid);
        likeRepository.delete(postid);
    }

    public long countByPostId(Long postid){
        return likeRepository.count(postid);
    }

    public LikeDto apply2Like(LikeDto reqeustLike, UserDto user){
        long postid = reqeustLike.getPostid();
        long id = reqeustLike.getId();
        String author = user.getNickname();
        String authorEmail = user.getEmail();
        return new LikeDto(id,postid, author,authorEmail);
    }





}
