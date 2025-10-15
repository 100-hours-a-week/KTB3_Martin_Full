package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;

public class CommentDto {

    private Long id;
    private Long postid;
    private String author = "unknown";
    private String authorEmail = "unknown";
    @NotBlank(message = "comment content should not empty")
    private String content;
    private String birthTime;

    @JsonIgnore
    private Boolean isdeleted = false;

    public CommentDto() {
    }
    public CommentDto(Long postID, String content, String authorEmail) {
        this.postid = postID;
        this.content = content;
        this.authorEmail = authorEmail;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPostid() {
        return postid;
    }
    public void setPostId(Long postID) {
        this.postid = postID;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }
    public String getAuthorEmail() {
        return authorEmail;
    }
    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }
    public String getBirthTime() {
        return birthTime;
    }
    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }



}
