package com.example._th_assignment.controller;

import com.example._th_assignment.dto.CommentDto;
import com.example._th_assignment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public String postComment(@PathVariable Long postId,
                              @ModelAttribute CommentDto newcomment) {
        String tmp = newcomment.getContent().replaceAll("\\s", "");
        if(!tmp.equals("")) commentService.saveComment(postId, newcomment);

        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return "redirect:/posts/" + postId;
    }

}
