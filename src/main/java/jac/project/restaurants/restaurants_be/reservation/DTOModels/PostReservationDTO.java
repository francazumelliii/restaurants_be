package jac.project.restaurants.restaurants_be.reservation.DTOModels;

public class PostReservationDTO {
    private String mail;
    private String date;
    private Integer quantity;
    private Integer turn_id;
    private Integer restaurant_id;
    private String customer_mail;

    public PostReservationDTO(){}
    public PostReservationDTO(String mail, String date, Integer quantity, Integer turn_id, Integer restaurant_id, String customer_mail){
        this.mail = mail;
        this.date = date;
        this.quantity = quantity;
        this.turn_id = turn_id;
        this.restaurant_id = restaurant_id;
        this.customer_mail = customer_mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTurn_id() {
        return turn_id;
    }

    public void setTurn_id(Integer turn_id) {
        this.turn_id = turn_id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getCustomer_mail() {
        return customer_mail;
    }

    public void setCustomer_mail(String customer_mail) {
        this.customer_mail = customer_mail;
    }
}

