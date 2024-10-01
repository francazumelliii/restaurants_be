package jac.project.restaurants.restaurants_be.village;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jac.project.restaurants.restaurants_be.county.County;
import jakarta.persistence.*;

@Entity
public class Village {
    @Id
    @Column(name = "village_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "cadastal_code")
    private char cadastalCode;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "longitude")
    private float longitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_id")
    private County county;

    public Village(){}

    public Village(int id, String name, char cadastalCode, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.cadastalCode = cadastalCode;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public char getCadastalCode() {
        return cadastalCode;
    }

    public void setCadastalCode(char cadastalCode) {
        this.cadastalCode = cadastalCode;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}
