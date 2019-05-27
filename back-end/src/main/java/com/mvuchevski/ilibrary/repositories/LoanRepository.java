package com.mvuchevski.ilibrary.repositories;

import com.mvuchevski.ilibrary.models.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

}
