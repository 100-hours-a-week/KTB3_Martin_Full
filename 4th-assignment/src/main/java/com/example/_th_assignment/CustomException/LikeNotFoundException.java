package com.example._th_assignment.CustomException;

public class LikeNotFoundException extends DtoNotFoundException{
    private final long postid;
    private final String email;

    public LikeNotFoundException(long postId, String email) {
        super("Like not found with email:" + email + " in postid:" + postId);
        this.postid = postId;
        this.email = email;
    }
}