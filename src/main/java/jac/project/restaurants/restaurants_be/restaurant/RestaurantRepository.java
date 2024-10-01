package jac.project.restaurants.restaurants_be.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Query("SELECT DISTINCT r FROM Restaurant r WHERE " +
            "r.name LIKE %:toSearch% OR " +
            "r.description LIKE %:toSearch% OR " +
            "r.village.name LIKE %:toSearch% OR " +
            "r.village.county.name LIKE %:toSearch% OR " +
            "r.company.name LIKE %:toSearch%")
    List<Restaurant> findRestaurantsBySearchText(@Param("toSearch") String toSearch);

    @Query("SELECT r FROM Restaurant r JOIN r.admins a WHERE a.mail = :mail")
    public List<Restaurant> getAdminRestaurants(@Param("mail") String mail);

    @Query("SELECT r, count(re) as orders FROM Restaurant r JOIN r.reservations re WHERE re.customer.mail = :mail GROUP BY r.id ORDER BY orders DESC ")
    List<Restaurant> findFavoriteRestaurants(@Param("mail") String mail);

    @Query("SELECT COALESCE(SUM(re.quantity), r.maxChairs) FROM Restaurant r LEFT JOIN r.reservations re " +
            "ON re.turn.id = :turnId AND re.date = :date WHERE r.id = :restaurantId " +
            "GROUP BY r.id, r.maxChairs")
    Integer findAvailableSeats(@Param("restaurantId") Integer restaurantId, @Param("date") LocalDate date, @Param("turnId") Integer turnId);

    @Query(
            "SELECT r, " +
                    "(6371 * acos( " +
                    "cos(radians(:latitude)) * cos(radians(r.latitude)) * " +
                    "cos(radians(r.longitude) - radians(:longitude)) + " +
                    "sin(radians(:latitude)) * sin(radians(r.latitude)) " +
                    ")) AS distance " +
                    "FROM Restaurant r " +
                    "ORDER BY (6371 * acos( " +
                    "cos(radians(:latitude)) * cos(radians(r.latitude)) * " +
                    "cos(radians(r.longitude) - radians(:longitude)) + " +
                    "sin(radians(:latitude)) * sin(radians(r.latitude)) " +
                    ")) ASC"
    )
    List<Restaurant> findNearestRestaurants(@Param("latitude") Float latitude, @Param("longitude") Float longitude);


    @Query("SELECT r.maxChairs FROM Restaurant r WHERE r.id = :id")
    Integer findTotalChairs(@Param("id") Integer id);
}
