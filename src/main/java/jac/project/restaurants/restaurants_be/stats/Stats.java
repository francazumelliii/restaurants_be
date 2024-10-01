package jac.project.restaurants.restaurants_be.stats;

public class Stats {
    private String name;
    private Integer value;

    public Stats(){}
    public Stats(String name, Integer value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
