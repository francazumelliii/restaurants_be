package jac.project.restaurants.restaurants_be.turn;

public class TurnDTO {
    private int id;
    private String start_time;
    private String end_time;

    public TurnDTO(){}
    public TurnDTO(int id, String start_time, String end_time){
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
