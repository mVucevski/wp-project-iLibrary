package com.mvuchevski.ilibrary.repositories;

import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByIsbn(String isbn);

    @Override
    Iterable<Book> findAll();

    Iterable<Book> findAllByIsbnContainingOrTitleContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(String isbn, String title, String authorName);

    Iterable<Book> findAllByGenre(String genre);

//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    public List<User> find(@Param("keyword") String keyword);
}
