package jac.project.restaurants.restaurants_be.coords;

public class CoordsDTO {
//export interface coords_obj{
//    latitude: number ,
//    longitude: number,
//    village: string,
//    county: string,
//    county_code: string,
//    region: string
//}
    private float latitude;
    private float longitude;
    private String village;
    private Integer village_id;
    private String county;
    private String county_code;
    private String region;
    public CoordsDTO(){}

    public CoordsDTO(float latitude, float longitude, String village, Integer village_id, String county, String county_code, String region) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.village = village;
        this.village_id = village_id;
        this.county = county;
        this.county_code = county_code;
        this.region = region;
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

    public Integer getVillage_id() {
        return village_id;
    }

    public void setVillage_id(Integer village_id) {
        this.village_id = village_id;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCounty_code() {
        return county_code;
    }

    public void setCounty_code(String county_code) {
        this.county_code = county_code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
