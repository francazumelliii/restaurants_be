package jac.project.restaurants.restaurants_be.customer;

public class CustomerDTO {

    private int id ;
    private String name;
    private String surname;
    private String mail;
    private String list;

    public CustomerDTO(){}
    public CustomerDTO(int id, String name, String surname, String mail){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
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


}
