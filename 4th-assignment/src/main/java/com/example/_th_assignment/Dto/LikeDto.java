package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LikeDto {
    private Long id;
    private Long postid;
    private String author = "unknown";
    private String authorEmail;


    @JsonIgnore
    private Boolean isdeleted = false;

    public LikeDto() {
    }
    public LikeDto(Long postid, String authorEmail) {
        this.postid = postid;
        this.authorEmail = authorEmail;
    }

    public LikeDto(long id, long postid, String author, String authorEmail) {
        this.id = id;
        this.postid = postid;
        this.author = author;
        this.authorEmail = authorEmail;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Long getPostid() {
        return postid;
    }
    public void setPostid(Long postid) {
        this.postid = postid;
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}