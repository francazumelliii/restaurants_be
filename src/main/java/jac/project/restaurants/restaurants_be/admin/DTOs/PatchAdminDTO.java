package jac.project.restaurants.restaurants_be.admin.DTOs;

public class PatchAdminDTO {
    private String name;
    private String surname;
    private String mail;
    private Integer restaurant_id;


    public PatchAdminDTO(){}
    public PatchAdminDTO(String name, String surname, String mail,Integer restaurant_id){
        this.name = name;
        this.surname = surname;
        this.mail = mail;
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

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
