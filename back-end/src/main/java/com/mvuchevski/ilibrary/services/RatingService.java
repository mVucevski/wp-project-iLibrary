package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.RatingExcepion;
import com.mvuchevski.ilibrary.models.Book;
import com.mvuchevski.ilibrary.models.Rating;
import com.mvuchevski.ilibrary.models.User;
import com.mvuchevski.ilibrary.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomUserDetailsService userService;

    public Rating addNewRating(Rating rating, String book_isbn, String username){
        Book book = bookService.findBookByISBN(book_isbn);
        User user = userService.loadUserByUsername(username);

        if(rating.getId()!=null){

            if(user.getRatings().stream().noneMatch(e->e.getId().equals(rating.getId()))){
                throw new RatingExcepion("You are trying to update another user's rating!");
            }
        }else{
            Optional<Rating> updatedRating = user.getRatings().stream().filter(e->e.getBook().getIsbn().equals(book_isbn)).findAny();
            updatedRating.ifPresent(e->rating.setId(e.getId()));
        }

        return saveBook(rating, book, user);
    }

    public Iterable<Rating> findAllRatingsByBook(String book_isbn){
        Book book = bookService.findBookByISBN(book_isbn);
        return ratingRepository.findAllByBook(book);
    }

    public Iterable<Rating> findAllRatingsByUser(String username){
        User user = userService.loadUserByUsername(username);
        return ratingRepository.findAllByUser(user);
    }

    private Rating saveBook(Rating rating, Book book, User user){
        try{
            rating.setBook(book);
            rating.setUser(user);
            if(rating.getRating()==null || rating.getRating() <=0 || rating.getRating() > 5) rating.setRating(1);

            return ratingRepository.save(rating);
        }catch (Exception ex){
            throw new RatingExcepion("Oopss, there has been a problem with the rating system. Please try again.");
        }
    }
}
