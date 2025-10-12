package com.example._th_assignment.service;

import com.example._th_assignment.dto.PostDto;
import com.example._th_assignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getAllPosts() {
        return postRepository.getAllList();
    }

    public PostDto getPost(long id) {
        return postRepository.getbyId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }




}
