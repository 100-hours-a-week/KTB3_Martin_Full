package com.example._th_assignment.ApiController;

import com.example._th_assignment.Dto.CommentDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Service.CommentService;
import com.example._th_assignment.Service.PostService;
import com.example._th_assignment.Service.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentAPiController {
    private final CommentService commentService;
    private final PostService postService;
    private final SessionManager sessionManager;

    @Autowired
    public CommentAPiController(CommentService commentService, PostService postService, SessionManager sessionManager) {
        this.commentService = commentService;
        this.postService = postService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/{postid}")
    public ResponseEntity<Map<String, Object>> getComments(@PathVariable Long postid, HttpServletRequest request){
        sessionManager.access2Resource(request);
        postService.getPost(postid);
        List<CommentDto> list = commentService.getByPostId(postid);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "get all comments success");
        response.put("comments",list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postid}/{id}")
    public ResponseEntity<Map<String, Object>> getComment(@PathVariable Long postid,
                                                 @PathVariable Long id, HttpServletRequest request){
        sessionManager.access2Resource(request);
        postService.getPost(postid);
        CommentDto comment = commentService.getByPostIdAndCommentId(postid, id);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "get comment success");
        response.put("comment", comment);
        return ResponseEntity.ok(Map.of("comment", comment));
    }

    @PostMapping("/{postid}")
    public ResponseEntity<Map<String, Object>>  postComment(
            @PathVariable Long postid, @Valid @RequestBody CommentDto comment, HttpServletRequest request){
        sessionManager.access2Auth(request);
        postService.getPost(postid);
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        comment.setAuthor(user.getNickname());
        comment.setAuthorEmail(user.getEmail());

        comment = commentService.saveComment(postid, comment);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "save comment success");
        response.put("comment",comment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{postid}/{id}")
                .buildAndExpand(comment.getPostid(), comment.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{postid}/{id}")
    public ResponseEntity<Map<String, Object>>  updateComment(
            @PathVariable Long postid, @PathVariable Long id,
            @Valid @RequestBody CommentDto comment, HttpServletRequest request){
        sessionManager.access2Resource(request);
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        if(!user.getEmail().equals(commentService.getByPostIdAndCommentId(postid, id).getAuthorEmail()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No Permission");

        CommentDto commentToUpdate = commentService.getByPostIdAndCommentId(postid, id);
        commentToUpdate.setContent(comment.getContent());
        commentToUpdate =  commentService.updateComment(postid,id, commentToUpdate);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "update comment success");
        response.put("comment",commentToUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postid}/{id}")
    public ResponseEntity<Map<String, Object>>  deleteComment(
            @PathVariable Long postid, @PathVariable Long id, HttpServletRequest request){
        sessionManager.access2Resource(request);
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        if(!user.getEmail().equals(commentService.getByPostIdAndCommentId(postid, id).getAuthorEmail()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No Permission");
        commentService.deleteComment(postid, id);

        return ResponseEntity.noContent().build();
    }


}
