package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //To-Do: Patch it
    public Book saveOrUpdateBook(Book book){
        //Exception handling
        return bookRepository.save(book);
    }

    public Book findBookByISBN(String isbn){
        Book book = bookRepository.findByIsbn(isbn);

        if(book==null){
            //throw exception
        }

        return book;
    }

    public Book findBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if(!book.isPresent()){
            //throw exception
        }

        return book.get();
    }

    public Iterable<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public void deleteBookByISBN(String isbn){
        Book book = bookRepository.findByIsbn(isbn);

        if(book==null){
            //throw exception
        }

        bookRepository.delete(book);
    }

    public void deleteBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if(!book.isPresent()){
            //throw exception
        }

        bookRepository.deleteById(id);
    }

    public Book updateBookByISBN(Book updatedBook, String isbn){

        Book book = bookRepository.findByIsbn(isbn);

        if(book==null){
            System.out.println("Book isbn doesnt exist");
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
