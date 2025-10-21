package com.example._th_assignment.ApiController;

import com.example._th_assignment.Dto.*;
import com.example._th_assignment.Service.*;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/posts")
public class PostApiController {


    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final SessionManager sessionManager;

    @Autowired
    public PostApiController(PostService postService, CommentService commentService, LikeService likeService, SessionManager sessionManager) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.sessionManager = sessionManager;
    }

    @GetMapping
    @JsonView(JsonViewGroup.summaryview.class)
    public ResponseEntity<Map<String, Object>> getPosts(HttpServletRequest request) {
        sessionManager.access2Resource(request);
        List<PostDto> posts = postService.getAllPosts();
        List<ResponsePostDto> responsePosts = new ArrayList<>();
        for (PostDto postDto : posts) {
            long commentnum = commentService.countByPostId((postDto.getId()));
            long likenum = likeService.countByPostId((postDto.getId()));
            responsePosts.add(postService.apply2ResponseDto(postDto, commentnum, likenum));
        }
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "get posts success");
        response.put("posts", responsePosts);

        return ResponseEntity.ok(Map.of("posts",responsePosts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPost(@PathVariable long id, HttpServletRequest request) {
        sessionManager.access2Resource(request);
        PostDto post = postService.getPost(id);
        post.setView(post.getView()+1);
        List<CommentDto> comments = commentService.getByPostId(id);
        List<LikeDto> likes = likeService.getbyPostId(id);

        Map <String,Object> map = new LinkedHashMap<>();
        map.put("post",postService.apply2ResponseDto(post,comments.size(),likes.size()));
        map.put("comments", commentService.getByPostId(id));

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "get post/"+id+ " success");
        response.put("post",map);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<Map<String,Object>> postPost(
            @Valid @RequestBody RequestPostDto requestPostDto, HttpServletRequest request){
        HttpSession session = sessionManager.access2Auth(request);
        UserDto user = (UserDto) session.getAttribute("user");

        PostDto post = postService.apply2PostDto(requestPostDto, new PostDto(user.getEmail()));
        post.setAuthor(user.getNickname());
        post = postService.savePost(post);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "save post success");
        response.put("post",post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> putPost(@PathVariable long id,
            @Valid @RequestBody RequestPostDto requestPostDto, HttpServletRequest request){
        sessionManager.access2Resource(request);
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        PostDto post = postService.getPost(id);
        if(!post.getAuthorEmail().equals(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No Permission");
        }

        post =  postService.apply2PostDto(requestPostDto, post);
        post = postService.updatePost(id, post);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "update post success");
        response.put("post",post);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deletePost(@PathVariable long id, HttpServletRequest request){
        sessionManager.access2Resource(request);
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        PostDto post = postService.getPost(id);

        if(!post.getAuthorEmail().equals(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No Permission");
        }
        postService.deletePost(id);
        commentService.deleteAllComment(id);
        likeService.deleteAllLike(id);

        return ResponseEntity.noContent().build();


    }


}



