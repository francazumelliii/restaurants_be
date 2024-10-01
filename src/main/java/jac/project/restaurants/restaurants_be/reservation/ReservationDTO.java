package jac.project.restaurants.restaurants_be.reservation;

import jac.project.restaurants.restaurants_be.customer.CustomerDTO;
import jac.project.restaurants.restaurants_be.restaurant.RestaurantDTO;

public class ReservationDTO {
    private int id;
    private String date;
    private int confirmed;
    private String mail;
    private String start_time;
    private String end_time;
    private int quantity;
    private CustomerDTO customer;
    private RestaurantDTO restaurant;

    public ReservationDTO(){}

    public ReservationDTO(int id, String date, int confirmed, String mail, String start_time, String end_time, int quantity, CustomerDTO customer, RestaurantDTO restaurant) {
        this.id = id;
        this.date = date;
        this.confirmed = confirmed;
        this.mail = mail;
        this.start_time = start_time;
        this.end_time = end_time;
        this.quantity = quantity;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
