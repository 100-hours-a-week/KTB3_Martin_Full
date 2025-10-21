package com.example._th_assignment.ApiController;

import com.example._th_assignment.ApiResponse.ApiResponse;
import com.example._th_assignment.Dto.CommentDto;
import com.example._th_assignment.Dto.UserDto;
import com.example._th_assignment.Service.CommentService;
import com.example._th_assignment.Service.PostService;
import com.example._th_assignment.Service.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CommentApiController {
    private final CommentService commentService;
    private final PostService postService;
    private final SessionManager sessionManager;

    @Autowired
    public CommentApiController(CommentService commentService, PostService postService, SessionManager sessionManager) {
        this.commentService = commentService;
        this.postService = postService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/{postid}")
    public ResponseEntity<Object> getComments(@PathVariable Long postid, HttpServletRequest request){
        sessionManager.access2Resource(request);
        postService.getPost(postid);
        List<CommentDto> list = commentService.getByPostId(postid);
        log.info("get all comments for postid={}",postid);

        return ResponseEntity.ok(ApiResponse.success("get all comments success", list));
    }

    @GetMapping("/{postid}/{id}")
    public ResponseEntity<Object> getComment(@PathVariable Long postid,
                                                 @PathVariable Long id, HttpServletRequest request){
        sessionManager.access2Resource(request);
        postService.getPost(postid);
        CommentDto comment = commentService.getByPostIdAndCommentId(postid, id);
        log.info("get comment for postid={}",postid);

        return ResponseEntity.ok(ApiResponse.success("get comment success", comment));
    }

    @PostMapping("/{postid}")
    public ResponseEntity<Object>  postComment(
            @PathVariable Long postid, @Valid @RequestBody CommentDto comment, HttpServletRequest request){
        sessionManager.access2Auth(request);
        postService.getPost(postid);
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        comment.setAuthor(user.getNickname());
        comment.setAuthorEmail(user.getEmail());

        comment = commentService.saveComment(postid, comment);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{postid}/{id}")
                .buildAndExpand(comment.getPostid(), comment.getId())
                .toUri();

        log.info("created comment for postid={}",postid);

        return ResponseEntity.created(location)
                .body(ApiResponse.success("create comment success", comment));
    }

    @PutMapping("/{postid}/{id}")
    public ResponseEntity<Object>  updateComment(
            @PathVariable Long postid, @PathVariable Long id,
            @Valid @RequestBody CommentDto comment, HttpServletRequest request){
        sessionManager.access2Resource(request);

        String writerEmail =commentService.getByPostIdAndCommentId(postid, id).getAuthorEmail();
        checkAuth(request,writerEmail);

        CommentDto commentToUpdate = commentService.getByPostIdAndCommentId(postid, id);
        commentToUpdate.setContent(comment.getContent());
        commentToUpdate =  commentService.updateComment(postid,id, commentToUpdate);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        return ResponseEntity.ok(ApiResponse.success("update comment success", commentToUpdate));
    }

    @DeleteMapping("/{postid}/{id}")
    public ResponseEntity<Map<String, Object>>  deleteComment(
            @PathVariable Long postid, @PathVariable Long id, HttpServletRequest request){
        sessionManager.access2Resource(request);

        String writerEmail = commentService.getByPostIdAndCommentId(postid, id).getAuthorEmail();

        checkAuth(request,writerEmail);
        commentService.deleteComment(postid, id);

        return ResponseEntity.noContent().build();
    }

    public void checkAuth(HttpServletRequest request, String writeremail){
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        String loggedEmail = user.getEmail();
        if(!loggedEmail.equals(writeremail))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No Permission for treat comment");
    }


}
