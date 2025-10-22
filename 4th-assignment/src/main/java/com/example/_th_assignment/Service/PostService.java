package com.example._th_assignment.Service;

import com.example._th_assignment.CustomException.PostNotFoundException;
import com.example._th_assignment.Dto.*;
import com.example._th_assignment.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {


    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getAllPosts() {
        return postRepository.getAllPosts();
    }

    public PostDto getPost(long id) {
        return postRepository.getbyId(id)
                .orElseThrow(()-> new PostNotFoundException(id));
    }

    public PostDto savePost(PostDto postDto){
        return postRepository.save(postDto);
    }

    public void deletePost(long id) {
        getPost(id);
        postRepository.delete(id);
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        getPost(id);
        return postRepository.update(id, postDto);
    }

    public ResponsePostDto apply2ResponsePostDto(PostDto postDto, long commentnum, long likenum) {
        return new ResponsePostDto(postDto, commentnum, likenum);
    }

    public ResponsePostAndCommentsDto apply2ResponsePostAndCommentsDto(ResponsePostDto responsepost, List<CommentDto> comments) {
        return new ResponsePostAndCommentsDto(responsepost, comments);
    }

    public PostDto apply2PostDto(RequestPostDto requestPostDto, PostDto postDto) {
        Long id = postDto.getId();
        String email = postDto.getAuthorEmail();
        String title = requestPostDto.getTitle();
        String content = requestPostDto.getContent();
        String author = postDto.getAuthor();
        long view = postDto.getView();
        String birthtime = postDto.getBirthtime();
        String image = "";

        if(requestPostDto.getImage()!=null){
            image = requestPostDto.getImage();
        }

        return new PostDto(id,email,title,content,author,view,birthtime,image);

    }


    public long count(){
        return postRepository.count();
    }





}
