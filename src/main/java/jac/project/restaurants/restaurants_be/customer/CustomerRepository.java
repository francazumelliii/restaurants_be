package jac.project.restaurants.restaurants_be.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c.list FROM Customer c WHERE c.mail = :mail")
    String findCustomerList(@Param("mail") String mail);

    Optional<Customer> findByMail(String mail);
}
