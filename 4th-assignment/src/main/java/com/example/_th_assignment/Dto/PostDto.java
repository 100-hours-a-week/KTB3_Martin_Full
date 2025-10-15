package com.example._th_assignment.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostDto {


    private Long id;
    @JsonIgnore
    private String authorEmail;


//    @Size(min = 1, max = 26, message = "title is not more than 25",
//            groups = {ValidationGroup.Postnewpost.class})
//    @NotBlank(message = "title should not empty", groups = {ValidationGroup.Postnewpost.class})
    private String title;

//    @NotBlank(message = "content should not empty", groups = {ValidationGroup.Postnewpost.class})
    private String content;

    private String author = "unknown";
    private int view = 0;
    private String birthtime ="";

    private String image = "";

    @JsonIgnore
    private Boolean isdeleted = false;

    public PostDto() {
    }

    public PostDto(String authorEmail){
        this.authorEmail = authorEmail;
    }

    public PostDto(String authorEmail,String title, String content) {
        id = 0L;
        this.authorEmail = authorEmail;
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
    public int getView() {
        return view;
    }
    public void setView(int view) {
        this.view = view;
    }
    public String getBirthtime() {
        return birthtime;
    }
    public void setBirthtime(String timeStamp) {
        this.birthtime = timeStamp;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }
    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }


}
