package com.mvuchevski.ilibrary.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @Column(updatable = false)
    private String bookISBN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    @Column(columnDefinition="DATETIME")
    private Date created_At;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    private Date end_At;

    // Every time the entity is created
    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.created_At);
        cal.add(Calendar.DATE, 1);
        this.end_At = cal.getTime();

    }

    public Reservation() {
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getEnd_At() {

        return end_At;
    }

    public void setEnd_At(Date end_At) {
        this.end_At = end_At;
    }

    public boolean isExpired(){
        Date currentDate = new Date();

        return currentDate.after(end_At);
    }
}
