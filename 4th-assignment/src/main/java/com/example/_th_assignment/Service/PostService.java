package com.example._th_assignment.Service;

import com.example._th_assignment.Dto.PostDto;
import com.example._th_assignment.Dto.RequestPostDto;
import com.example._th_assignment.Dto.ResponsePostDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {


    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getAllPosts() {
        return postRepository.getAllPosts();
    }

    public PostDto getPost(long id) {
        PostDto post =  postRepository.getbyId(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not found "));
        return post;
    }

    public PostDto savePost(PostDto postDto){
        return postRepository.save(postDto);
    }

    public void deletePost(long id) {
        postRepository.delete(id);
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        return postRepository.update(id, postDto);
    }

    public ResponsePostDto apply2ResponseDto(PostDto postDto, long commentnum, long likenum) {
        return new ResponsePostDto(postDto, commentnum, likenum);
    }

    public PostDto apply2PostDto(RequestPostDto requestPostDto, PostDto postDto) {
        postDto.setTitle(requestPostDto.getTitle());
        postDto.setContent(requestPostDto.getContent());
        if(requestPostDto.getImage()!=null){
            postDto.setImage(requestPostDto.getImage());
        }

        return postDto;
    }

    public long count(){
        return postRepository.count();
    }





}
