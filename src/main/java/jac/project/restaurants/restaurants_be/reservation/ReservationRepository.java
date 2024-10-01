package jac.project.restaurants.restaurants_be.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.confirmed = 1")
    public List<Reservation> findAll();

    @Query("SELECT r FROM Reservation r WHERE r.customer.mail = :mail AND r.confirmed = 1")
    List<Reservation> findReservationsByCustomerMail(@Param("mail") String mail);

    @Query("SELECT r FROM Reservation r WHERE r.restaurant.id = :id AND r.confirmed = 1 ORDER BY r.date DESC ")
    List<Reservation> getRestaurantReservations(@Param("id") Integer id);

    @Query("SELECT count(r.id) FROM Reservation r WHERE r.restaurant.id = :id AND r.turn.start >= :start AND r.turn.end <= :end AND r.confirmed = 1 AND r.date = :date")
    Integer findDayTimeStats(@Param("id") Integer id, @Param("start") LocalTime start, @Param("end") LocalTime end, @Param("date") LocalDate date);


    @Query("SELECT SUM(COALESCE(r.quantity,0))" +
            "FROM Reservation r " +
            "WHERE r.restaurant.id = :id AND r.date = :date " +
            "AND r.confirmed = 1 " +
            "AND r.turn.start = :start AND r.turn.end = :end")
    Integer findTotalReservedNow(@Param("id") Integer id,
                                       @Param("date") LocalDate date,
                                       @Param("start") LocalTime start,
                                       @Param("end") LocalTime end);




}
