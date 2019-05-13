package com.mvuchevski.ilibrary.repositories;

import com.mvuchevski.ilibrary.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByIsbn(String isbn);

    @Override
    Iterable<Book> findAll();
}
