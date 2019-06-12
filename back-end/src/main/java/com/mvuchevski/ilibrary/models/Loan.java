package com.mvuchevski.ilibrary.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @Column(updatable = false)
    private String bookISBN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private Integer fee = 0;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    @Column(columnDefinition="DATETIME")
    private LocalDateTime created_At;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    @Column(columnDefinition="DATETIME")
    private LocalDateTime due_date;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    @Column(columnDefinition="DATETIME")
    private LocalDateTime returned_At;

    @PrePersist
    protected void onCreate(){
        this.created_At = LocalDateTime.now();

        this.due_date = this.created_At.plusDays(14);
    }

    public Loan() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getFee() {
        LocalDateTime dateNow = LocalDateTime.now();

        if(dateNow.isAfter(due_date)){
            long daysBetween = DAYS.between(due_date, dateNow);

            fee = (int)(daysBetween / 7) + 1;
        }

        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public LocalDateTime getCreated_At() {
        return created_At;
    }

    public void setCreated_At(LocalDateTime created_At) {
        this.created_At = created_At;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public LocalDateTime getReturned_At() {
        return returned_At;
    }

    public void setReturned_At(LocalDateTime returned_At) {
        this.returned_At = returned_At;
    }
}
