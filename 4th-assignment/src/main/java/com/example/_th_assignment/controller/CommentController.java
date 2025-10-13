package com.example._th_assignment.controller;

import com.example._th_assignment.dto.CommentDto;
import com.example._th_assignment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/{postId}/{commentId}")
    public String getCommentForm(@PathVariable Long postId, @PathVariable Long commentId, Model model) {
        CommentDto comment = commentService.getByPostIdAndCommentId(postId, commentId);
        model.addAttribute("comment", comment);

//        System.out.println(">>> 모델 전달 확인: " + model.containsAttribute("comment"));
//        CommentDto dto = (CommentDto) model.getAttribute("comment");
//        System.out.println(">>> comment = " + dto);
//        System.out.println(">>> comment.postid = " + dto.getPostid());
        return "comments/form";
    }

    @PutMapping("/{postId}/{commentId}")
    public String putComment(@PathVariable Long postId, @PathVariable Long commentId,
                             @ModelAttribute CommentDto commentdto) {
        commentService.updateComment(postId, commentId, commentdto);
        return "redirect:/posts/" + postId;
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
