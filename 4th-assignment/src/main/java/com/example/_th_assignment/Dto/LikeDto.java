package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;


public class LikeDto {
    private Long id;
    private Long postid;
    private String authorEmail;
    private boolean isdeleted = false;



    public LikeDto() {
    }
    public LikeDto(Long postid, String authorEmail) {
        this.postid = postid;
        this.authorEmail = authorEmail;
    }

    public LikeDto(long id, long postid, String authorEmail) {
        this.id = id;
        this.postid = postid;
        this.authorEmail = authorEmail;
    }


    public Long getPostid() {
        return postid;
    }
    public void setPostid(Long postid) {
        this.postid = postid;
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