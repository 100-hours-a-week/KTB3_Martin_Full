package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;


public class ResponsePostDto {

    @JsonView(JsonViewGroup.summaryview.class)
    private String title;

    private String content;

    @JsonView(JsonViewGroup.summaryview.class)
    private String author = "unknown";

    @JsonView(JsonViewGroup.summaryview.class)
    private long view;
    @JsonView(JsonViewGroup.summaryview.class)
    private String birthtime;
    @JsonView(JsonViewGroup.summaryview.class)
    private long comments;
    @JsonView(JsonViewGroup.summaryview.class)
    private long likes;

    public ResponsePostDto() {}

    public ResponsePostDto(PostDto postDto, long comments, long likes) {


        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.author = postDto.getAuthor();
        this.view = postDto.getView();
        this.birthtime = postDto.getBirthtime();
        this.comments = comments;
        this.likes = likes;
    }

    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }

    public String getAuthor(){
        return author;
    }

    public long getView(){
        return view;
    }

    public String getBirthtime(){
        return birthtime;
    }

    public long getComments(){
        return comments;
    }
    public long getLikes(){
        return likes;
    }


}
