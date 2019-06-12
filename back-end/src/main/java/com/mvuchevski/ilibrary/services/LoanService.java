package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.BookAvailableCopiesException;
import com.mvuchevski.ilibrary.exceptions.LoanNotReturnedException;
import com.mvuchevski.ilibrary.exceptions.UserMembershipException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Loan;
import com.mvuchevski.ilibrary.models.User;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import com.mvuchevski.ilibrary.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static com.mvuchevski.ilibrary.AppConstants.MAX_LOANS_PER_USER;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CustomUserDetailsService userService;

    public Loan createNewLoan(String book_isbn, String username){

        Book book = bookService.findBookByISBN(book_isbn);
        User user = userService.loadUserByUsername(username);
        Set<Loan> loans = user.getLoans();

        if(user.isMemebershipExpired()){
            throw new UserMembershipException("Please start or renew your membership before making reservation!");
        }

        if(loans.stream().filter(l->l.getReturned_At()==null).count() >= MAX_LOANS_PER_USER){
            throw new LoanNotReturnedException("The user hasn't returned all of his previous books. You must return all of the loaned books before taking a new one.");
        }

        if(user.getReservations().stream().anyMatch(e->e.getBookISBN().equals(book_isbn))){
            reservationService.deleteResByBookISBN(book_isbn,username);
        }else if(book.getCopiesLeft() <= 0){
            throw new BookAvailableCopiesException("The Book with ISBN: '" + book_isbn + "' doesn't have available copies at the moment!");
        }


        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBookISBN(book_isbn);
        loan.setUser(user);

        return loanRepository.save(loan);
    }

    public void returnLoanedBook(String isbn, String username){
        Set<Loan> loans = findAllByBook(isbn);
        Optional<Loan> loan = loans.stream().filter(e->e.getReturned_At()==null).filter(l->l.getUser().getUsername().equals(username)).findAny();

        if(!loan.isPresent()){
            throw new LoanNotReturnedException("There isn't active loan for the book with ISBN: '" + isbn + "' for the user: '" + username + "'");
        }

        loan.get().setReturned_At(LocalDateTime.now());
        loanRepository.save(loan.get());
    }

    public Iterable<Loan> getAllLoans(String username){
        User user = userService.loadUserByUsername(username);

        return loanRepository.findAllByUser(user);
    }

    public Set<Loan> findAllByBook(String book_isbn){
        Book book = bookService.findBookByISBN(book_isbn);

        return book.getLoans();
    }
}
