package jac.project.restaurants.restaurants_be.imgs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgsRepository extends JpaRepository<Imgs, Integer> {
    @Query("SELECT i FROM Imgs i WHERE i.restaurant.id = :id")
    List<Imgs> findAllImgsFromRestaurantId(@Param("id") Integer id );

}
