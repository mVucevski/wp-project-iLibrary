package com.mvuchevski.ilibrary.exceptions;


public class UserMembershipExceptionResponse {
    private String userMembershipException;

    public UserMembershipExceptionResponse(String userMembershipException) {
        this.userMembershipException = userMembershipException;
    }

    public String getUserMembershipException() {
        return userMembershipException;
    }

    public void setUserMembershipException(String userMembershipException) {
        this.userMembershipException = userMembershipException;
    }
}
