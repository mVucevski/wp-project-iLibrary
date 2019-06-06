package com.mvuchevski.ilibrary.web;

import com.mvuchevski.ilibrary.models.Loan;
import com.mvuchevski.ilibrary.models.Rating;
import com.mvuchevski.ilibrary.services.MapValidationErrorService;
import com.mvuchevski.ilibrary.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/rating")
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{book_isbn}")
    public ResponseEntity<?> addRating(@Valid @RequestBody Rating rating, BindingResult result, @PathVariable String book_isbn, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null) return errorMap;

        Rating newRating = ratingService.addNewRating(rating, book_isbn, principal.getName());

        return new ResponseEntity<Rating>(newRating, HttpStatus.CREATED);
    }

    @GetMapping("/{book_isbn}")
    public Iterable<?> getRatingsByBook(@PathVariable String book_isbn) {
        return ratingService.findAllRatingsByBook(book_isbn);
    }

}
