package jac.project.restaurants.restaurants_be.reservation.DTOModels;

public class AvailableSeatsDTO {
    private Integer total;
    private Integer available;

    public AvailableSeatsDTO(){}

    public AvailableSeatsDTO(Integer total, Integer available) {
        this.total = total;
        this.available = available;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
