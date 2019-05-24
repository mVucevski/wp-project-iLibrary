package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.BookAvailableCopiesException;
import com.mvuchevski.ilibrary.exceptions.ReservationNotFoundException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Reservation;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import com.mvuchevski.ilibrary.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookService bookService;

    public Reservation createNewReservation(String book_isbn){

        Book book = bookService.findBookByISBN(book_isbn);


        if(book.getAvailableCopies() <= 0){
            throw new BookAvailableCopiesException("The Book with ISBN: '" + book_isbn + "' doesn't have available copies at the moment!");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);


        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setBookISBN(book_isbn);


        return reservationRepository.save(reservation);
    }

    public Iterable<Reservation> getAllReservations(){
        //checkExpiredReservation();
        return reservationRepository.findAll();
    }

    public Iterable<Reservation> findAllByBook(String isbn){
        checkExpiredReservationByISBN(isbn);

        Book book = bookService.findBookByISBN(isbn);

        return book.getReservations();
        //return reservationRepository.findAllByBookISBN(isbn);
    }

    private boolean checkExpiredReservationByISBN(String isbn){
        Iterable<Reservation> reservations = reservationRepository.findAllByBookISBN(isbn);

        for(Reservation r : reservations){
            if(r.isExpired()){
                deleteReservation(r);
            }
        }
        return true;
    }

    private void deleteReservation(Reservation r){
        Book book = r.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        reservationRepository.delete(r);
    }

    public void deleteReservationById(Long id){
        Optional<Reservation> r = reservationRepository.findById(id);

        if(!r.isPresent()){
            throw new ReservationNotFoundException("Reservation with ID: '" + id + "' was not found!");
        }

        deleteReservation(r.get());
    }
}
