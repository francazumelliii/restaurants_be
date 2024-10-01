package jac.project.restaurants.restaurants_be.region;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jac.project.restaurants.restaurants_be.county.County;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {
    @Id
    @Column(name = "region_id")
    public int id;
    @Column(name = "name")
    private String name;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "longitude")
    private float longitude;
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<County> counties;

    public Region(){
        this.counties = new HashSet<>();
    }

    public Region(int id, String name, float latitude, float longitude, Set<County> counties) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.counties = counties;
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

    public Set<County> getCounties() {
        return counties;
    }

    public void setCounties(Set<County> counties) {
        this.counties = counties;
    }
}
