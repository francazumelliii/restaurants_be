package jac.project.restaurants.restaurants_be.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jac.project.restaurants.restaurants_be.county.County;
import jac.project.restaurants.restaurants_be.entrepreneur.Entrepreneur;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Company {
    @Id
    @Column(name = "company_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "vat_n")
    private String vat;
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrepreneur_id")
    public Entrepreneur entrepreneur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_id")
    @JsonBackReference
    public County county;


    public Company(){}

    public Company(int id, String name, String vat, String address, String telephone, Entrepreneur entrepreneur, County county) {
        this.id = id;
        this.name = name;
        this.vat = vat;
        this.address = address;
        this.telephone = telephone;
        this.entrepreneur = entrepreneur;
        this.county = county;
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

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}
