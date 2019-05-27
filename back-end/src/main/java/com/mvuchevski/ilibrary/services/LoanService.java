package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.BookAvailableCopiesException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Loan;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import com.mvuchevski.ilibrary.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReservationService reservationService;

    public Loan createNewLoan(String book_isbn){

        Book book = bookService.findBookByISBN(book_isbn);

        if(book.getCopiesLeft() <= 0){
            throw new BookAvailableCopiesException("The Book with ISBN: '" + book_isbn + "' doesn't have available copies at the moment!");
        }

        reservationService.deleteResByBookISBN(book_isbn);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBookISBN(book_isbn);


        return loanRepository.save(loan);
    }

    public Iterable<Loan> getAllLoans(){return loanRepository.findAll();}

    public Iterable<Loan> findAllByBook(String book_isbn){
        Book book = bookService.findBookByISBN(book_isbn);

        return book.getLoans();
    }
}
