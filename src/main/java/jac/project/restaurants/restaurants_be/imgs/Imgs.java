package jac.project.restaurants.restaurants_be.imgs;

import jac.project.restaurants.restaurants_be.restaurant.Restaurant;
import jakarta.persistence.*;

@Entity
public class Imgs {
    @Id
    @Column(name = "img_id")
    private int id;
    @Column(name = "path")
    private String path;
    @Column(name = "priority")
    private int priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    public Imgs(){}

    public Imgs(int id, String path, int priority, Restaurant restaurant) {
        this.id = id;
        this.path = path;
        this.priority = priority;
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
