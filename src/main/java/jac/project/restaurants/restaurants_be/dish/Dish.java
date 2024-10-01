package jac.project.restaurants.restaurants_be.dish;

import jac.project.restaurants.restaurants_be.dish_category.DishCategory;
import jac.project.restaurants.restaurants_be.menu.Menu;
import jakarta.persistence.*;

@Entity
public class Dish {
    @Id
    @Column(name = "dish_id")
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "ingredients")
    private String ingredients;
    @Column(name = "vegan")
    private Integer isVegan;
    @Column(name = "lactose_free")
    private Integer isLactoseFree;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_category_id")
    private DishCategory dishCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;
    public Dish(){}

    public Dish(int id, String description, String ingredients, Integer isVegan, Integer isLactoseFree, String name, float price, DishCategory dishCategory, Menu menu) {
        this.id = id;
        this.description = description;
        this.ingredients = ingredients;
        this.isVegan = isVegan;
        this.isLactoseFree = isLactoseFree;
        this.name = name;
        this.price = price;
        this.dishCategory = dishCategory;
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer isVegan() {
        return isVegan;
    }

    public void setVegan(Integer vegan) {
        isVegan = vegan;
    }

    public Integer isLactoseFree() {
        return isLactoseFree;
    }

    public void setLactoseFree(Integer lactoseFree) {
        isLactoseFree = lactoseFree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
