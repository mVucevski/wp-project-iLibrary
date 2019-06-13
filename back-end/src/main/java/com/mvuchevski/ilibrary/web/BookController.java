package com.mvuchevski.ilibrary.web;

import com.mvuchevski.ilibrary.exceptions.ImageUploadException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.services.BookService;
import com.mvuchevski.ilibrary.services.ImagesService;
import com.mvuchevski.ilibrary.services.MapValidationErrorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import static com.mvuchevski.ilibrary.AppConstants.FILE_PATH_IMG_STORAGE;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ImagesService imagesService;

    @PostMapping("")
    public ResponseEntity<?> createNewBook(@Valid @RequestBody Book book, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null) return errorMap;

        Book newBook = bookService.saveOrUpdateBook(book);
        return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookByISBN(@PathVariable String isbn) {

        Book book = bookService.findBookByISBN(isbn);

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {

        Book book = bookService.findBookById(id);

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBookByISBN(@PathVariable String isbn) {
        bookService.deleteBookByISBN(isbn);

        return new ResponseEntity<String>("Book with ISBN: " + isbn + " was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);

        return new ResponseEntity<String>("Book with ID: " + id + " was deleted", HttpStatus.OK);
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, BindingResult result,
                                        @PathVariable String isbn) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null) return errorMap;

        Book updateBook = bookService.updateBookByISBN(book, isbn);

        return new ResponseEntity<Book>(updateBook, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/search/{keyword}")
    public Iterable<Book> searchBooks(@PathVariable String keyword) {
        if (keyword == null) {
            return bookService.findAllBooks();
        }

        return bookService.searchBooks(keyword);
    }

    @GetMapping("/category/{genre}")
    public Iterable<Book> getAllBooksByGenre(@PathVariable String genre) {

        return bookService.findAllByGenre(genre);
    }

    @PostMapping("/uploadImg")
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws Exception {

        if (file == null) {
            throw new ImageUploadException("Please upload an jpeg or png image");
        }

        String imagePath = imagesService.uploadImage(file);

        return new ResponseEntity<String>(imagePath, HttpStatus.OK);
    }

    @GetMapping(
            value = "/image/{name}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable String name) throws IOException {

       return imagesService.getImage(name);

    }

}
