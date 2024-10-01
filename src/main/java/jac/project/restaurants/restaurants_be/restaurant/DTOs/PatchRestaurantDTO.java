package jac.project.restaurants.restaurants_be.restaurant.DTOs;

public class PatchRestaurantDTO {


    private String name;
    private String street;
    private String street_number;
    private Float latitude;
    private Float longitude;
    private Integer max_chairs;
    private String description;
    private String banner;
    private Integer village;


    public PatchRestaurantDTO(){}
    public PatchRestaurantDTO(String name, String street, String street_number, Float latitude, Float longitude, Integer max_chairs, String description, String banner, Integer village){
        this.name = name;
        this.street = street;
        this.street_number = street_number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.max_chairs = max_chairs;
        this.description = description;
        this.banner = banner;
        this.village = village;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getMax_chairs() {
        return max_chairs;
    }

    public void setMax_chairs(Integer max_chairs) {
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

    public Integer getVillage() {
        return village;
    }

    public void setVillage(Integer village) {
        this.village = village;
    }
}
