package com.example._th_assignment.ApiController;

import com.example._th_assignment.Dto.JsonViewGroup;
import com.example._th_assignment.Dto.PostDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Dto.ValidationGroup;
import com.example._th_assignment.Service.CommentService;
import com.example._th_assignment.Service.PostService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/posts")
public class PostApiController {


    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostApiController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> createPost(
            @Validated(ValidationGroup.Postnewpost.class) @RequestBody PostDto postDto,
            HttpServletRequest request) {
        PostDto post = postService.savePost(postDto);

        HttpSession session = request.getSession(false);
        if (session != null) {
            UserDto user = (UserDto) session.getAttribute("user");
            post.setAuthor(user.getNickname());
        }

//        return new ResponseEntity<>(post, HttpStatus.OK);
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("message", "save post success");
        map.put("post", post);
        return ResponseEntity.ok(map);
    }

    @JsonView(JsonViewGroup.summaryview.class)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getPosts(){
        return ResponseEntity.ok(Map.of("posts",postService.getAllPosts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPost(@PathVariable long id){
        Map <String,Object> map = new LinkedHashMap<>();
        map.put("post",postService.getPost(id));
        map.put("comments", commentService.getByPostId(id));
        return ResponseEntity.ok(map);
    }

}



