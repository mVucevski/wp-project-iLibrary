package com.mvuchevski.ilibrary.exceptions;

public class BookIsbnExceptionResponse {

    private String isbn;

    public BookIsbnExceptionResponse(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
