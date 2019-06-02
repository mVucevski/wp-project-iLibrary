package com.mvuchevski.ilibrary.repositories;

import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Iterable<Reservation> findAllByBookISBN(String bookISBN);

    Integer deleteReservationByBookISBN(String bookISBN);
}
