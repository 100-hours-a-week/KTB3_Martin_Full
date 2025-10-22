package com.example._th_assignment.CustomException;

public class CommentNotFoundException extends DtoNotFoundException {
    private long postId;
    private long id;
    public CommentNotFoundException(long postId, long id) {
        super("Comment not found with id:" + id + " in post id:" + postId);
        this.postId = postId;
        this.id = id;
    }
}
