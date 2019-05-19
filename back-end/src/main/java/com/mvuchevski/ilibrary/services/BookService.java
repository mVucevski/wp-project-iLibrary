package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.BookIsbnException;
import com.mvuchevski.ilibrary.exceptions.BookNotFoundException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveOrUpdateBook(Book book){

        //Exception handling
        if(book.getId() != null){
            Book existingBook = findBookByISBN(book.getIsbn());
        }

        if(book.getCoverUrl().length() < 1){
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
}
