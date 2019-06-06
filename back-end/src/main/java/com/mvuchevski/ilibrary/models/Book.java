package com.mvuchevski.ilibrary.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title is required")
    private String title;

    @NotBlank(message = "Author's name is required")
    private String authorName;

    private String description;

    private String language;

    @NotNull(message = "Please enter valid number")
    @Min(value = 0, message = "The value can't be negative")
    private Integer availableCopies = 0;

    //TO-DO
    //Make separate entity for the genres
    private String genre;


    //TO-DO
    //Upload the image to the server
    private String coverUrl;

    @NotBlank(message = "ISBN is required")
    @Column(unique = true, updatable = false)
    @Size(min=10, max=13, message = "Please use valid ISBN")
    private String isbn;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date publicationDate;


    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "book", orphanRemoval = true)
    List<Reservation> reservations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "book", orphanRemoval = true)
    Set<Loan> loans = new TreeSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "book", orphanRemoval = true)
    Set<Rating> ratings = new TreeSet<>();

    public Book(){}

    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Integer getAvailableCopies() {

        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public void setCopiesLeft(Integer copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

    @Transient
    private Integer copiesLeft = 0;

    @Transient
    public Integer getCopiesLeft(){
        int copiesToBorrow = availableCopies - reservations.size() - loans.size();
        if(copiesToBorrow < 0) copiesToBorrow = 0;
        return copiesToBorrow;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    @Transient
    public Double getTotalRatingScore(){
        int sum = ratings.stream().mapToInt(Rating::getRating).sum();

        System.out.println("SUM RATING: " + sum);

        if(ratings.size() == 0) return 0.0;

        return (double) sum / (double) ratings.size();
    }

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }


}
