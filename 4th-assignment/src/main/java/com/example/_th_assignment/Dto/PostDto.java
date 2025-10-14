package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;

public class PostDto {

    private Long id;
    @NotBlank(message = "title should not empty")
    private String title;
    @NotBlank(message = "contect should not empty")
    private String content;
    private String author = "unknown";
    @JsonIgnore
    private Boolean isdeleted = false;

    public PostDto() {
    }
    public PostDto(String title, String content) {
        id = 0L;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }


}
