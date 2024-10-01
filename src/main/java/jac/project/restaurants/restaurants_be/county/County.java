package jac.project.restaurants.restaurants_be.county;

import com.fasterxml.jackson.annotation.*;
import jac.project.restaurants.restaurants_be.company.Company;
import jac.project.restaurants.restaurants_be.region.Region;
import jac.project.restaurants.restaurants_be.village.Village;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class County {
    @Id
    @Column(name = "county_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "longitude")
    private float longitude;
    @OneToMany(mappedBy = "county", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    Set<Company> companies;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonBackReference
    public Region region;
    @OneToMany(mappedBy = "county", fetch = FetchType.LAZY)
    Set<Village> villages;


    public County(){
        this.villages = new HashSet<>();
        this.companies = new HashSet<>();
    }

    public County(int id, String name, String code, float latitude, float longitude, Set<Company> companies, Region region, Set<Village> villages) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.companies = companies;
        this.region = region;
        this.villages = villages;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<Village> getVillages() {
        return villages;
    }

    public void setVillages(Set<Village> villages) {
        this.villages = villages;
    }
}
