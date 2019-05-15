package com.mvuchevski.ilibrary.web;

import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.services.BookService;
import com.mvuchevski.ilibrary.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewBook(@Valid @RequestBody Book book, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null) return errorMap;

        Book newBook = bookService.saveOrUpdateBook(book);
        return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookByISBN(@PathVariable String isbn){

        Book book = bookService.findBookByISBN(isbn);

        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){

        Book book = bookService.findBookById(id);

        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBookByISBN(@PathVariable String isbn){
        bookService.deleteBookByISBN(isbn);

        return new ResponseEntity<String>("Book with ISBN: "+isbn+" was deleted",HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);

        return new ResponseEntity<String>("Book with ID: "+id+" was deleted",HttpStatus.OK);
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, BindingResult result,
                                        @PathVariable String isbn){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null) return errorMap;

        Book updateBook = bookService.updateBookByISBN(book, isbn);

        return new ResponseEntity<Book>(updateBook,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Book> getAllBooks(){return bookService.findAllBooks();}
}
