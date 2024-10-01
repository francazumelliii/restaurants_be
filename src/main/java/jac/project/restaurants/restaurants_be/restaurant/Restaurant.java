package jac.project.restaurants.restaurants_be.restaurant;

import jac.project.restaurants.restaurants_be.admin.Admin;
import jac.project.restaurants.restaurants_be.company.Company;
import jac.project.restaurants.restaurants_be.imgs.Imgs;
import jac.project.restaurants.restaurants_be.menu.Menu;
import jac.project.restaurants.restaurants_be.reservation.Reservation;
import jac.project.restaurants.restaurants_be.review.Review;
import jac.project.restaurants.restaurants_be.village.Village;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurant {
    @Id
    @Column(name = "restaurant_id")
    public int id;
    @Column(name = "name")
    private String name;
    @Column(name = "rating")
    private int rating;
    @Column(name = "street")
    private String street;
    @Column(name = "street_number")
    private String streetNumber;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "longitude")
    private float longitude;
    @Column(name = "max_chairs")
    private int maxChairs;
    @Column(name = "description")
    private String description;
    @Column(name = "banner")
    private String banner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "village_id")
    private Village village;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    Set<Admin> admins;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    Set<Imgs> imgs;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    Set<Menu> menus;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    Set<Reservation> reservations;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    Set<Review> reviews;

    public Restaurant(){
        this.admins = new HashSet<>();
        this.imgs = new HashSet<>();
        this.menus = new HashSet<>();
        this.reservations = new HashSet<>();
        this.reviews = new HashSet<>();
    }

    public Restaurant(int id, String name, int rating, String street, String streetNumber, float latitude, float longitude, int maxChairs, String description, String banner, Village village, Company company, Set<Admin> admins, Set<Imgs> imgs, Set<Menu> menus, Set<Reservation> reservations, Set<Review> reviews) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.street = street;
        this.streetNumber = streetNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxChairs = maxChairs;
        this.description = description;
        this.banner = banner;
        this.village = village;
        this.company = company;
        this.admins = admins;
        this.imgs = imgs;
        this.menus = menus;
        this.reservations = reservations;
        this.reviews = reviews;
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

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public int getMaxChairs() {
        return maxChairs;
    }

    public void setMaxChairs(int maxChairs) {
        this.maxChairs = maxChairs;
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

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

    public Set<Imgs> getImgs() {
        return imgs;
    }

    public void setImgs(Set<Imgs> imgs) {
        this.imgs = imgs;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
