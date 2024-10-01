package jac.project.restaurants.restaurants_be.customer;

import jac.project.restaurants.restaurants_be.authentication.CustomUserDetailsService;
import jac.project.restaurants.restaurants_be.authentication.JwtTokenUtil;
import jac.project.restaurants.restaurants_be.customer.DTOs.PatchCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CustomerService {
    public final CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Autowired
    private CustomUserDetailsService userDetailsService;

    public String getCustomerList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        if(mail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }else{
            return this.customerRepository.findCustomerList(mail);
        }
    }

    public Optional<CustomerDTO> getCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        if (mail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            return this.customerRepository.findByMail(mail).map(this::toCustomerDTO);
        }
    }
    public Optional<Customer> getCustomerEntity(String mail){
        if(mail == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: mail");
        }else{
            return this.customerRepository.findByMail(mail);
        }
    }
    public Optional<CustomerDTO> patchCustomer(PatchCustomerDTO dto){
        if(dto.getMail() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: mail");
        }
        Optional<Customer> optionalCustomer = this.customerRepository.findByMail(dto.getMail());
        Customer customer = optionalCustomer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Entity not found"));
        if(dto.getName() != null){
            customer.setName(dto.getName());
        }
        if(dto.getSurname() != null){
            customer.setSurname(dto.getSurname());
        }
        Customer savedCustomer = this.customerRepository.save(customer);
        return Optional.ofNullable(toCustomerDTO(savedCustomer));
    }


    public String registerCustomer(Customer customer) {
        if (customer.getMail() != null) {
            Optional<Customer> optionalCustomer = this.customerRepository.findByMail(customer.getMail());
            if (optionalCustomer.isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User with same mail is already present");
            }
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setList("[{\"key\":\"Home\",\"value\":\"homepage\"},{\"key\":\"Account\",\"value\":\"account\"}]");

        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Saved customer: " + savedCustomer);

        UserDetails userDetails = userDetailsService.loadUserByUsername(savedCustomer.getMail());
        System.out.println("Loaded UserDetails: " + userDetails);

        String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Generated token: " + token);

        if (token == null || token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate JWT token");
        }

        return token;
    }




    //DTO
    public CustomerDTO toCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getSurname(), customer.getMail());

    }
    //FROM DTO TO ENTITY

}
