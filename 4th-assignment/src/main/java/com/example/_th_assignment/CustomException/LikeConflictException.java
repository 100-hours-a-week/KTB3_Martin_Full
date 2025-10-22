package com.example._th_assignment.CustomException;

public class LikeConflictException extends DtoConflictException {
    private final long postid;
    private final String email;
    public LikeConflictException(long postid, String email) {
        super("Like conflict with postid:" + postid + " and email:" + email);
        this.postid = postid;
        this.email = email;
    }

    public String explain(){
        return "only one \"like\" is possible for user in 1 post";
    }
}
