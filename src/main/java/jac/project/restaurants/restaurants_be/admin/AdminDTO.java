package jac.project.restaurants.restaurants_be.admin;

public class AdminDTO {
    private int id;
    private String name;
    private String surname;
    private String mail;
    public AdminDTO(){}
    public AdminDTO(int id, String name, String surname,String mail){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
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
}
