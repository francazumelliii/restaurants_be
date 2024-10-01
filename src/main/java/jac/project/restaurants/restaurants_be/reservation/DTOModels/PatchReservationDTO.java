package jac.project.restaurants.restaurants_be.reservation.DTOModels;

public class PatchReservationDTO {
    private Integer turn_id;
    private Integer quantity;
    private String mail;
    private String date;


    public PatchReservationDTO(Integer turn_id, Integer quantity, String mail, String date){
        this.turn_id = turn_id;
        this.quantity = quantity;
        this.mail = mail;
        this.date = date;
    }
    public PatchReservationDTO(){}

    public Integer getTurn_id() {
        return turn_id;
    }

    public void setTurn_id(Integer turn_id) {
        this.turn_id = turn_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
