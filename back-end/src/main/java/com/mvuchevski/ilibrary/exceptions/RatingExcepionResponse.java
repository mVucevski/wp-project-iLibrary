package com.mvuchevski.ilibrary.exceptions;

public class RatingExcepionResponse {
    private String ratingException;

    public RatingExcepionResponse(String ratingException) {
        this.ratingException = ratingException;
    }

    public String getRatingException() {
        return ratingException;
    }

    public void setRatingException(String ratingException) {
        this.ratingException = ratingException;
    }
}
