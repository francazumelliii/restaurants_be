package jac.project.restaurants.restaurants_be.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query("SELECT a.list FROM Admin a WHERE a.mail = :mail")
    String findAdminList(@Param("mail") String mail);
    Optional<Admin> findByMail(String mail);
}
