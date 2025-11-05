package com.example._th_assignment.Service;

import com.example._th_assignment.CustomException.LikeConflictException;
import com.example._th_assignment.CustomException.LikeNotFoundException;
import com.example._th_assignment.CustomException.PostNotFoundException;
import com.example._th_assignment.CustomException.UserNotFoundException;
import com.example._th_assignment.Dto.LikeDto;
import com.example._th_assignment.Dto.PostDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Entity.Post;
import com.example._th_assignment.Entity.PostLike;
import com.example._th_assignment.Entity.User;
import com.example._th_assignment.JpaRepository.PostJpaRepository;
import com.example._th_assignment.JpaRepository.PostLikeJpaRepository;
import com.example._th_assignment.JpaRepository.UserJpaRepository;
import com.example._th_assignment.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserJpaRepository userJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final PostLikeJpaRepository postLikeJpaRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository,
                       UserJpaRepository userJpaRepository,
                       PostJpaRepository postRepository, PostLikeJpaRepository postLikeJpaRepository) {
        this.likeRepository = likeRepository;
        this.userJpaRepository = userJpaRepository;
        this.postJpaRepository = postRepository;
        this.postLikeJpaRepository = postLikeJpaRepository;
    }

    public PostLike findByPostIdAndAuthorEmail(long postId, String authorEmail) {
        PostLike postLike = postLikeJpaRepository.findByPost_IdAndUser_Email(postId, authorEmail)
                .orElseThrow(() -> new LikeNotFoundException(postId, authorEmail));
        return postLike;
    }

    @Transactional(readOnly = true)
    public List<LikeDto> getbyPostId(Long postId) {
        List<PostLike> postLikes = postLikeJpaRepository.findAllByPost_Id(postId);
        List<LikeDto> likeDtos = new ArrayList<>();
        for (PostLike postLike : postLikes) {
            LikeDto likeDto = postLike.toDto();
            likeDtos.add(likeDto);

        }
        return likeDtos;
    }
    @Transactional(readOnly = true)
    public LikeDto getbyPostIdAndAuthorEmail(Long postId, String authorEmail){
        PostLike postLike =  findByPostIdAndAuthorEmail(postId, authorEmail);
        return postLike.toDto();
    }




    public LikeDto saveLike(Long postid, LikeDto likeDto){
        String email = likeDto.getAuthorEmail();
        Boolean flag = postLikeJpaRepository.existsByPost_IdAndUser_Email(postid, email);

        if(flag) throw new LikeConflictException(postid, email);
        Post post = postJpaRepository.findByidAndIsdeletedFalse(postid)
                .orElseThrow(() -> new PostNotFoundException(postid));
        User user = userJpaRepository.findByEmailAndIsdeletedFalse(email)
                .orElseThrow(() -> new UserNotFoundException(email));



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
        return new LikeDto(id,postid, authorEmail);
    }





}
