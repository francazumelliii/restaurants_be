package jac.project.restaurants.restaurants_be.dish_category;

import jac.project.restaurants.restaurants_be.dish.Dish;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DishCategory {
    @Id
    @Column(name = "dish_category_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "dishCategory", fetch = FetchType.LAZY)
    Set<Dish> dishes;

    public DishCategory(){
        this.dishes = new HashSet<>();
    }

    public DishCategory(int id, String name, String description, Set<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishes = dishes;
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

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }
}

