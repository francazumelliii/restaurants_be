package jac.project.restaurants.restaurants_be.authentication;

import jac.project.restaurants.restaurants_be.admin.Admin;
import jac.project.restaurants.restaurants_be.admin.AdminRepository;
import jac.project.restaurants.restaurants_be.customer.Customer;
import jac.project.restaurants.restaurants_be.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;
    private String role;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByMail(email)
                .orElse(null);

        if (customer != null) {
            this.role = "customer";
            return new User(
                    customer.getMail(), customer.getPassword(), AuthorityUtils.createAuthorityList("customer")
            );
        }

        Admin admin = adminRepository.findByMail(email)
                .orElse(null);

        if (admin != null) {
            this.role = "admin";
            return new User(
                    admin.getMail(), admin.getPassword(), AuthorityUtils.createAuthorityList("admin")
            );
        }

        throw new UsernameNotFoundException("Email not found: " + email);
    }


    public String getRole() {
        return this.role;
    }
}
