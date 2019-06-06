package com.mvuchevski.ilibrary.repositories;

import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Rating;
import com.mvuchevski.ilibrary.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {

    Iterable<Rating> findAllByBook(Book book);

    Iterable<Rating> findAllByUser(User user);
}
