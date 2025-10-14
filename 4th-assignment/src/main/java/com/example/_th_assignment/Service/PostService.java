package com.example._th_assignment.Service;

import com.example._th_assignment.Dto.PostDto;
import com.example._th_assignment.Repository.PostRepository;
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
        return postRepository.getAllList();
    }

    public PostDto getPost(long id) {
        return postRepository.getbyId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"is not here"));
    }

    public PostDto savePost(PostDto postDto)throws ResponseStatusException {
        if(postDto.getTitle() == null||postDto.getTitle().equals("")||
        postDto.getContent() == null || postDto.getContent().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return postRepository.save(postDto);
    }

    public void deletePost(long id) {
        postRepository.delete(id);
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        return postRepository.update(id, postDto);
    }





}
