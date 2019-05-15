package com.mvuchevski.ilibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookIsbnException extends RuntimeException{

    public BookIsbnException(String msg){
        super(msg);
    }
}
