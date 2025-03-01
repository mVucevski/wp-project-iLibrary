package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.BookIsbnException;
import com.mvuchevski.ilibrary.exceptions.BookNotFoundException;
import com.mvuchevski.ilibrary.exceptions.ImageUploadException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mvuchevski.ilibrary.AppConstants.FILE_PATH_IMG_STORAGE;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveOrUpdateBook(Book book){

        //Exception handling
        if(book.getId() != null){
            Book existingBook = findBookByISBN(book.getIsbn());
        }

        if(book.getCoverUrl()==null || book.getCoverUrl().length() < 1){
            book.setCoverUrl("https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/2410/9780241003435.jpg");
        }

        try{
            return bookRepository.save(book);
        }catch(Exception ex){
            throw new BookIsbnException("Book ISBN: " + book.getIsbn() + " already exists.");
        }

    }

    public Book findBookByISBN(String isbn){
        Book book = bookRepository.findByIsbn(isbn);

        if(book == null){
            throw new BookIsbnException("Book with ISBN: " + isbn + " doesn't exist in the database.");
        }

        return book;
    }

    public Book findBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if(!book.isPresent()){
            throw new BookNotFoundException("Book with Id: " + id + " doesn't exist.");
        }

        return book.get();
    }

    public Iterable<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public void deleteBookByISBN(String isbn){
        Book book = bookRepository.findByIsbn(isbn);

        if(book == null){
            throw new BookIsbnException("Book with ISBN: " + isbn + " doesn't exist.");
        }

        bookRepository.delete(book);
    }

    public void deleteBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if(!book.isPresent()){
            throw new BookNotFoundException("Book with Id: " + id + " doesn't exist.");
        }

        bookRepository.deleteById(id);
    }

    public Book updateBookByISBN(Book updatedBook, String isbn){

        Book book = bookRepository.findByIsbn(isbn);

        if(book==null){
            throw new BookNotFoundException("Book with Id: " + updatedBook.getId() + " doesn't exist.");
        }else{
            updatedBook.setId(book.getId());
            if(updatedBook.getTitle() == null){
                updatedBook.setTitle(book.getTitle());
            }
            if(updatedBook.getAuthorName() == null){
                updatedBook.setAuthorName(book.getAuthorName());
            }
            if(updatedBook.getIsbn() == null){
                updatedBook.setIsbn(book.getIsbn());
            }
        }

        return bookRepository.save(updatedBook);
    }

    public Iterable<Book> searchBooks(String keyword){
        if(keyword == ""){
            return bookRepository.findAll();
        }
        return bookRepository.findAllByIsbnContainingOrTitleContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(keyword, keyword, keyword);
    }

    public Iterable<Book> findAllByGenre(String genre){
        if(genre == null && genre.length() == 0){
            return new ArrayList<>();
        }

        return bookRepository.findAllByGenre(genre);
    }



}
