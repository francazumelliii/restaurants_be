package jac.project.restaurants.restaurants_be.turn;

import jac.project.restaurants.restaurants_be.reservation.Reservation;
import jakarta.persistence.*;
import org.hibernate.dialect.pagination.FetchLimitHandler;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Turn {
    @Id
    @Column(name = "turn_id")
    private int id;
    @Column(name = "start_time")
    private LocalTime start;
    @Column(name = "end_time")
    private LocalTime end;
    @OneToMany(mappedBy = "turn", fetch = FetchType.LAZY)
    Set<Reservation> reservations;
    public Turn(){
        this.reservations = new HashSet<>();
    }

    public Turn(int id, LocalTime start, LocalTime end, Set<Reservation> reservations) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
