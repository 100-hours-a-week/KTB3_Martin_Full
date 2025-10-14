package com.example._th_assignment.controller;

import com.example._th_assignment.dto.CommentDto;
import com.example._th_assignment.dto.PostDto;
import com.example._th_assignment.service.CommentService;
import com.example._th_assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());

        return "posts/list";
    }
    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("comments", commentService.getByPostId(id));
        model.addAttribute("newcomment", new CommentDto());
        return "posts/detail";
    }

    @GetMapping("/form")
    public String getPostForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "posts/form";
    }

    @PostMapping
    public String savePost(@ModelAttribute PostDto postDto) {
        try{
            postService.savePost(postDto);
        }catch(ResponseStatusException e){
            System.out.println("입력자 오류 발생");
        }finally{

            return "redirect:/posts";
        }

    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/form")
    public String getEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("postDto", postService.getPost(id));
        return "posts/form";
    }

    @PutMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostDto postDto) {
        postService.updatePost(id, postDto);
        return "redirect:/posts/" + id;

    }

}
