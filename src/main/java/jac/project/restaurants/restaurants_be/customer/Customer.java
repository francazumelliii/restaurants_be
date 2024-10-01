package jac.project.restaurants.restaurants_be.customer;

import jac.project.restaurants.restaurants_be.reservation.Reservation;
import jac.project.restaurants.restaurants_be.review.Review;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class
Customer {
    @Id
    @Column(name = "customer_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name  = "mail", unique = true)
    private String mail;
    @Column(name = "password")
    private String password;
    @Column(name = "list")
    private String list;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    Set<Reservation> reservations;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    Set<Review> reviews;

    public Customer(){
        this.reservations = new HashSet<>();
        this.reviews = new HashSet<>();
    }

    public Customer(int id, String name, String surname, String mail, String password, String list, Set<Reservation> reservations, Set<Review> reviews) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.list = list;
        this.reservations = reservations;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
