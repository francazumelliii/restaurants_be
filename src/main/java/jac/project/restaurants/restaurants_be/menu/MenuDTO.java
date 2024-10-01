package jac.project.restaurants.restaurants_be.menu;

import jac.project.restaurants.restaurants_be.dish.DishDTO;

import java.util.List;

public class MenuDTO {
    private int id;
    private String name;
    private String description;
    private String category;
    private List<DishDTO> dishes;

    public MenuDTO(){}

    public MenuDTO(int id, String name, String description, String category, List<DishDTO> dishes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }
}
