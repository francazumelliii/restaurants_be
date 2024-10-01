package jac.project.restaurants.restaurants_be.customer.DTOs;

public class PatchCustomerDTO {
    private String mail;
    private String name;
    private String surname;

    public PatchCustomerDTO(){}
    public PatchCustomerDTO(String mail, String name, String surname){
        this.mail = mail;
        this.name = name;
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
}
