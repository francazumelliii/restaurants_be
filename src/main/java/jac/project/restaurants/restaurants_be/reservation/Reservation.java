package jac.project.restaurants.restaurants_be.reservation;

import jac.project.restaurants.restaurants_be.customer.Customer;
import jac.project.restaurants.restaurants_be.restaurant.Restaurant;
import jac.project.restaurants.restaurants_be.turn.Turn;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "confirmed")
    private int confirmed;
    @Column(name = "mail")
    private String mail;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turn_id")
    private Turn turn;

    public Reservation(){}

    public Reservation(int id, int quantity, int confirmed, String mail, LocalDate date, Customer customer, Restaurant restaurant, Turn turn) {
        this.id = id;
        this.quantity = quantity;
        this.confirmed = confirmed;
        this.mail = mail;
        this.date = date;
        this.customer = customer;
        this.restaurant = restaurant;
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
