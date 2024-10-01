package jac.project.restaurants.restaurants_be.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT DISTINCT m FROM Menu m WHERE m.restaurant.id = :id")
    List<Menu> findAllByRestaurant(@Param("id") Integer id);
}
