package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.BookAvailableCopiesException;
import com.mvuchevski.ilibrary.exceptions.LoanNotReturnedException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Loan;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import com.mvuchevski.ilibrary.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

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

        if(reservationService.findAllByBook(book_isbn).iterator().hasNext()){
            reservationService.deleteResByBookISBN(book_isbn);
        }

        //TO-DO: Proverka dali korisnikot veke ima zemeno kniga
        boolean hasNotReturnedBook = book.getLoans().stream().anyMatch(e->e.getReturned_At()==null);

        if(hasNotReturnedBook){
            throw new LoanNotReturnedException("The user hasn't returned all of his previous books. You must return all of the loaned books before taking a new one.");
        }

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBookISBN(book_isbn);




        return loanRepository.save(loan);
    }

    public void returnLoanedBook(String isbn){
        Set<Loan> loans = findAllByBook(isbn);

        Optional<Loan> l = loans.stream().filter(e->e.getReturned_At()==null).findAny();

        if(!l.isPresent()){
            //To-Do Make custom exception
            throw new LoanNotReturnedException("There isn't active loan for the book with ISBN: " + isbn);
        }

        l.get().setReturned_At(LocalDateTime.now());
        loanRepository.save(l.get());
    }

    public Iterable<Loan> getAllLoans(){return loanRepository.findAll();}

    public Set<Loan> findAllByBook(String book_isbn){
        Book book = bookService.findBookByISBN(book_isbn);

        return book.getLoans();
    }
}
