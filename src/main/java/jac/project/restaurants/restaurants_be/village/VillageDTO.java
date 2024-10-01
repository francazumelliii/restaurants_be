package jac.project.restaurants.restaurants_be.village;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VillageDTO {
    private int id;
    private String name;
    private char cadastralCode;
    private float latitude;
    private float longitude;

    public VillageDTO() {
        // Default constructor
    }

    public VillageDTO(int id, String name, char cadastralCode, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.cadastralCode = cadastralCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public char getCadastralCode() {
        return cadastralCode;
    }

    public void setCadastralCode(char cadastralCode) {
        this.cadastralCode = cadastralCode;
    }

    @JsonProperty
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @JsonProperty
    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
