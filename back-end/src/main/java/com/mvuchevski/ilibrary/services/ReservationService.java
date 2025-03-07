package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.BookAvailableCopiesException;
import com.mvuchevski.ilibrary.exceptions.ReservationNotFoundException;
import com.mvuchevski.ilibrary.exceptions.ReservationsLoansLimitException;
import com.mvuchevski.ilibrary.exceptions.UserMembershipException;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Reservation;
import com.mvuchevski.ilibrary.models.User;
import com.mvuchevski.ilibrary.repositories.BookRepository;
import com.mvuchevski.ilibrary.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import static com.mvuchevski.ilibrary.AppConstants.MAX_RESERVATIONS_PER_USER;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomUserDetailsService userService;

    public Reservation createNewReservation(String book_isbn, String username){

        Book book = bookService.findBookByISBN(book_isbn);

        if(book.getCopiesLeft() <= 0){
           throw new BookAvailableCopiesException("The Book with ISBN: '" + book_isbn + "' doesn't have available copies at the moment!");
       }

        User user = userService.loadUserByUsername(username);
        Set<Reservation> reservations = user.getReservations();

        if(user.isMemebershipExpired()){
            throw new UserMembershipException("Please start or renew your membership before making reservation!");
        }

        if(reservations.size() >= MAX_RESERVATIONS_PER_USER){
            throw new ReservationsLoansLimitException("You have reached the limit of active reservations!");
        }

        if(reservations.stream().anyMatch(r->r.getBookISBN().equals(book_isbn))){
            throw new ReservationsLoansLimitException("You already have active reservation for this book");
        };

        try{
            Reservation reservation = new Reservation();
            reservation.setBook(book);
            reservation.setBookISBN(book_isbn);
            reservation.setUser(user);
            reservation.setUsername(username);

            return reservationRepository.save(reservation);
        }catch(Exception ex){
            throw new ReservationNotFoundException("There was a problem with making reservation.");
        }

    }

    public Iterable<Reservation> getAllReservations(String username){

        User user = userService.loadUserByUsername(username);

        return reservationRepository.findAllByUsername(username);
    }

    public Iterable<Reservation> findAllByBook(String isbn){
        checkExpiredReservationByISBN(isbn);

        Book book = bookService.findBookByISBN(isbn);

        return book.getReservations();
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
        //book.setAvailableCopies(book.getAvailableCopies() + 1);
        reservationRepository.delete(r);
    }

    public void deleteReservationById(Long id){
        Optional<Reservation> r = reservationRepository.findById(id);

        if(!r.isPresent()){
            throw new ReservationNotFoundException("Reservation with ID: '" + id + "' was not found!");
        }

        deleteReservation(r.get());
    }

    @Transactional
    public void deleteResByBookISBN(String isbn, String username){

        User user = userService.loadUserByUsername(username);

        Integer tmp = reservationRepository.deleteReservationByBookISBNAndUsername(isbn, username);

        if(tmp==0){
            throw new ReservationNotFoundException("Reservation with Book ISBN: '" + isbn + "' was not found!");
        }

    }
}
