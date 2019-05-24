package com.mvuchevski.ilibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleBookIsbnException(BookIsbnException ex, WebRequest request){
        BookIsbnExceptionResponse exceptionResponse = new BookIsbnExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest request){
        BookNotFoundExceptionResponse exceptionResponse = new BookNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleBookAvailableCopiesException(BookAvailableCopiesException ex, WebRequest request){
        BookAvailableCopiesExceptionResponse exceptionResponse = new BookAvailableCopiesExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleReservationNotFoundException(ReservationNotFoundException ex, WebRequest request){
        ReservationNotFoundExceptionResponse exceptionResponse = new ReservationNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



}
