package jac.project.restaurants.restaurants_be.authentication;

public class AuthenticationRequest {
    private String mail;
    private String password;

    public AuthenticationRequest(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public AuthenticationRequest() {
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
}
