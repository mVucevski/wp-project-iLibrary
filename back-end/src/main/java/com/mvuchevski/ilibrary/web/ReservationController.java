package com.mvuchevski.ilibrary.web;

import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Reservation;
import com.mvuchevski.ilibrary.services.MapValidationErrorService;
import com.mvuchevski.ilibrary.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{book_isbn}")
    public ResponseEntity<?> createNewReservation(@PathVariable String book_isbn){

        Reservation newReservation = reservationService.createNewReservation(book_isbn);
        return new ResponseEntity<Reservation>(newReservation, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Reservation> getAllReservations(){return reservationService.getAllReservations();}

    @GetMapping("/{book_isbn}")
    public Iterable<Reservation> getAllReservationsByBook(@PathVariable String book_isbn){

        return reservationService.findAllByBook(book_isbn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservationById(@PathVariable Long id){
        reservationService.deleteReservationById(id);

        return new ResponseEntity<String>("Reservation with ID: "+id+" was deleted",HttpStatus.OK);
    }
}
