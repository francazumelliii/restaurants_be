package jac.project.restaurants.restaurants_be.restaurant;

import jac.project.restaurants.restaurants_be.admin.AdminDTO;
import jac.project.restaurants.restaurants_be.company.Company;
import jac.project.restaurants.restaurants_be.company.CompanyDTO;
import jac.project.restaurants.restaurants_be.coords.CoordsDTO;
import jac.project.restaurants.restaurants_be.county.CountyDTO;
import jac.project.restaurants.restaurants_be.imgs.Imgs;
import jac.project.restaurants.restaurants_be.imgs.ImgsDTO;

import java.util.List;

public class RestaurantDTO {
    private int id;
    private String name;
    private int rating;
    private String street;
    private String street_number;
    private int max_chairs;
    private String description;
    private String banner;
    private List<AdminDTO> admin;
    private CompanyDTO company;
    private ImgsDTO img;
    private CoordsDTO coords;
    public RestaurantDTO(){}
    public RestaurantDTO(int id, String name, int rating, String street, String street_number, int max_chairs,
                         String description, String banner, List<AdminDTO> admin, CompanyDTO company, ImgsDTO img, CoordsDTO coords){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.street = street;
        this.street_number = street_number;
        this.max_chairs = max_chairs;
        this.description = description;
        this.banner = banner;
        this.admin = admin;
        this.company = company;
        this.img = img;
        this.coords = coords;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public int getMax_chairs() {
        return max_chairs;
    }

    public void setMax_chairs(int max_chairs) {
        this.max_chairs = max_chairs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<AdminDTO> getAdmin() {
        return admin;
    }

    public void setAdmin(List<AdminDTO> admin) {
        this.admin = admin;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public ImgsDTO getImg() {
        return img;
    }

    public void setImg(ImgsDTO img) {
        this.img = img;
    }

    public CoordsDTO getCoords() {
        return coords;
    }

    public void setCoords(CoordsDTO coords) {
        this.coords = coords;
    }
}



