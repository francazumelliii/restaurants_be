package jac.project.restaurants.restaurants_be.review;

import jac.project.restaurants.restaurants_be.customer.Customer;
import jac.project.restaurants.restaurants_be.reservation.Reservation;
import jac.project.restaurants.restaurants_be.restaurant.Restaurant;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
public class Review {
    @Id
    @Column(name = "review_id")
    private int id;
    @Column(name = "rating")
    private int rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Review(){}

    public Review(int id, int rating, String comment, LocalDate date, Customer customer, Restaurant restaurant) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
