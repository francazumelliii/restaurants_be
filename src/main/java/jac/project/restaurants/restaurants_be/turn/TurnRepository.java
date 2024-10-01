package jac.project.restaurants.restaurants_be.turn;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface TurnRepository extends JpaRepository<Turn, Integer> {
    Optional<Turn> findByStart(LocalTime start);
}
