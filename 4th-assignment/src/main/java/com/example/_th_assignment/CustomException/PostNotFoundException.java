package com.example._th_assignment.CustomException;

public class PostNotFoundException extends DtoNotFoundException {
    private final long postId;

    public PostNotFoundException(long postId) {
        super("Post not found with id:" + postId);
        this.postId = postId;
    }
}
