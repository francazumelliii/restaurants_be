package jac.project.restaurants.restaurants_be.dish;

public class DishDTO {
    private int id;
    private String name;
    private String description;
    private String ingredients;
    private boolean is_vegan;
    private boolean is_lactose_free;
    private String category_name;
    private double price;

    public DishDTO(){}
    public DishDTO(int id, String name, String description, String ingredients, boolean is_vegan, boolean is_lactose_free,
                   String category_name, double price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.is_lactose_free = is_lactose_free;
        this.is_vegan = is_vegan;
        this.category_name = category_name;
        this.price = price;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isIs_vegan() {
        return is_vegan;
    }

    public void setIs_vegan(boolean is_vegan) {
        this.is_vegan = is_vegan;
    }

    public boolean isIs_lactose_free() {
        return is_lactose_free;
    }

    public void setIs_lactose_free(boolean is_lactose_free) {
        this.is_lactose_free = is_lactose_free;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
