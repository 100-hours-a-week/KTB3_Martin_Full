package com.example._th_assignment.ApiController;


import com.example._th_assignment.Dto.LikeDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Service.LikeService;
import com.example._th_assignment.Service.PostService;
import com.example._th_assignment.Service.SessionManager;
import com.example._th_assignment.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/likes")
public class LikeApiController {

    private final LikeService likeService;
    private final PostService postService;
    private final SessionManager sessionManager;

    @Autowired
    public LikeApiController(LikeService likeService, PostService postService, SessionManager sessionManager) {
        this.likeService = likeService;
        this.postService = postService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/{postid}")
    public ResponseEntity<Map<String, Object>> getLikes(
            @PathVariable Long postid, @RequestParam(value = "user", required = false) String email,
            HttpServletRequest request) {
        sessionManager.access2Resource(request);
        postService.getPost(postid);

        if(email ==null) {
            List<LikeDto> list = likeService.getbyPostId(postid);
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("message", "get likes success");
            response.put("likes", list);
            return ResponseEntity.ok(response);
        }
        LikeDto like = likeService.getbyPostIdAndAuthorEmail(postid, email);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "get like success");
        response.put("likes", like);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> saveLike(@PathVariable Long postId, HttpServletRequest request) {
        sessionManager.access2Auth(request);
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        LikeDto like = new LikeDto(postId, user.getEmail());
        like.setAuthor(user.getNickname());

        like = likeService.saveLike(postId, like);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "save like success");
        response.put("like", like);

        URI location = URI.create("/likes/" + postId + "?user=" + like.getAuthorEmail());

        return ResponseEntity.created(location).body(response);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> updateLike(@PathVariable Long postId, HttpServletRequest request) {
        sessionManager.access2Resource(request);
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        LikeDto like = likeService.getbyPostIdAndAuthorEmail(postId, user.getEmail());
        like.setAuthor(user.getNickname());
        like = likeService.updateLike(postId,user.getEmail(), like);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "update like success");
        response.put("like", like);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> deleteLike(@PathVariable Long postId, HttpServletRequest request) {
        sessionManager.access2Resource(request);
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        //like가 있는지 확인
        likeService.getbyPostIdAndAuthorEmail(postId, user.getEmail());

        likeService.deleteLike(postId,user.getEmail());

        return ResponseEntity.noContent().build();
    }


}
