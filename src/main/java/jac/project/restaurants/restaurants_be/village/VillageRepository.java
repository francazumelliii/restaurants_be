package jac.project.restaurants.restaurants_be.village;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {
    @Query("SELECT v FROM Village v WHERE v.county.id = :id")
    List<Village> getVillagesByCountyId(@Param("id") Integer id);
}
