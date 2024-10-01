package jac.project.restaurants.restaurants_be.admin.DTOs;

public class PostAdminDTO {
    private String name;
    private String surname;
    private String mail;
    private String password;
    private Integer restaurant_id;


    public PostAdminDTO(){}

    public PostAdminDTO(String name, String surname, String mail, String password, Integer restaurant_id) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.restaurant_id = restaurant_id;
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

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
