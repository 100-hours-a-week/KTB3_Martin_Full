package com.example._th_assignment.CustomException;

public class LikeNotFoundException extends DtoNotFoundException{
    private final long postid;
    private final long id;
    public LikeNotFoundException(long postId, long id) {
        super("Like not found with id:" + id + " in post id:" + postId);
        this.postid = postId;
        this.id = id;
    }
}