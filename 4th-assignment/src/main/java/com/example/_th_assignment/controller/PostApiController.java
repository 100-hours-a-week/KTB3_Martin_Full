package com.example._th_assignment.controller;

import com.example._th_assignment.dto.PostDto;
import com.example._th_assignment.repository.PostRepository;
import com.example._th_assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/posts")
public class PostApiController {

    @Autowired
    private PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        PostDto post;
        try{

            post = postService.savePost(postDto);
        }catch(ResponseStatusException rse){
            return ResponseEntity.badRequest().body(Map.of("message", rse.getMessage()));
        }
//        return new ResponseEntity<>(post, HttpStatus.OK);
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("Message", "Post saved successfully");
        map.put("post", post);
        return ResponseEntity.ok(map);
    }



}



