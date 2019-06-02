package com.mvuchevski.ilibrary.web;

import com.mvuchevski.ilibrary.models.Loan;
import com.mvuchevski.ilibrary.models.Reservation;
import com.mvuchevski.ilibrary.services.LoanService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/loan")
@CrossOrigin
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/{book_isbn}")
    public ResponseEntity<?> createNewLoan(@PathVariable String book_isbn){
        Loan newLoan = loanService.createNewLoan(book_isbn);

        return new ResponseEntity<Loan>(newLoan,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Loan> getAllLoans(){return loanService.getAllLoans();}

    @GetMapping("/{book_isbn}")
    public Set<Loan> getAllLoansByBook(@PathVariable String book_isbn){

        return loanService.findAllByBook(book_isbn);
    }

    @DeleteMapping("/{book_isbn}")
    public ResponseEntity<?> returnBook(@PathVariable String book_isbn){
        loanService.returnLoanedBook(book_isbn);
        return new ResponseEntity<>("The loan for the book with ISBN: '" + book_isbn + "' was successfully returned.", HttpStatus.OK);
    }

}
