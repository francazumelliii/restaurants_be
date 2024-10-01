package jac.project.restaurants.restaurants_be.menu;

import jac.project.restaurants.restaurants_be.dish.Dish;
import jac.project.restaurants.restaurants_be.restaurant.Restaurant;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Menu {
    @Id
    @Column(name = "menu_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    Set<Dish> dishes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    public Menu(){
        this.dishes = new HashSet<>();
    }

    public Menu(int id, String name, String description, String category, Set<Dish> dishes, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.dishes = dishes;
        this.restaurant = restaurant;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
