package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Reservation;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import com.mvuchevski.ilibrary.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookService bookService;

    public Reservation createNewReservation(String book_isbn){

        Book book = bookService.findBookByISBN(book_isbn);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setBookISBN(book_isbn);

        return reservationRepository.save(reservation);
    }

    public Iterable<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Iterable<Reservation> findAllByBook(String isbn){
        bookService.findBookByISBN(isbn);

        return reservationRepository.findAllByBookISBN(isbn);
    }
}
