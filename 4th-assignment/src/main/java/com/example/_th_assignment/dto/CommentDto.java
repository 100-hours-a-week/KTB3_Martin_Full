package com.example._th_assignment.dto;

public class CommentDto {
    private Long id;
    private Long postID;
//    private String author;
    private String content;
    private Boolean isdeleted = false;

    public CommentDto() {
    }
    public CommentDto(Long postID, String content) {
        this.postID = postID;
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPostID() {
        return postID;
    }
    public void setPostID(Long postID) {
        this.postID = postID;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Boolean getIsdeleted() {
        return isdeleted;
    }
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }



}
