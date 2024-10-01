package jac.project.restaurants.restaurants_be.company;

public class CompanyDTO {

    private int id;
    private String name;
    private String vat;
    private String address;
    private String telephone;
    public CompanyDTO(){}
    public CompanyDTO(int id, String name, String vat, String address, String telephone){
        this.id = id;
        this.name = name;
        this.vat = vat;
        this.address = address;
        this.telephone = telephone;

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

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
