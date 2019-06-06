package com.mvuchevski.ilibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RatingExcepion extends RuntimeException{
    public RatingExcepion(String message) {
        super(message);
    }
}
