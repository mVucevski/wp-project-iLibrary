package com.mvuchevski.ilibrary.repositories;

import com.mvuchevski.ilibrary.models.Loan;
import com.mvuchevski.ilibrary.models.Reservation;
import com.mvuchevski.ilibrary.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    Iterable<Loan> findAllByUser(User user);
}
