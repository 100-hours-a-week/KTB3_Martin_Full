package com.example._th_assignment.Dto;

public class LikeDto {
    private String author;
    private Long postid;
    private Long id;

    private Boolean isdeleted = false;
    public LikeDto(String author, Long postid, Long id, Boolean isdelete){}

    public LikeDto() {
    }
    public LikeDto(Long postid, String author) {
        this.postid = postid;
        this.author = author;
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}
    public Boolean getIsdeleted() {
        return isdeleted;
    }
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

}