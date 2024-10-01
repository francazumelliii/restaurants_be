package jac.project.restaurants.restaurants_be.customer;


import jac.project.restaurants.restaurants_be.APIResponse.ApiResponse;
import jac.project.restaurants.restaurants_be.customer.DTOs.PatchCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    public final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/me/list")
    public ResponseEntity<ApiResponse<String>> getCustomerList(){
        ApiResponse response = new ApiResponse(true, this.customerService.getCustomerList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Optional<CustomerDTO>>> getCustomer(){
        ApiResponse response = new ApiResponse(true, this.customerService.getCustomer());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PatchMapping("")
    public ResponseEntity<ApiResponse<Optional<CustomerDTO>>> patchCustomer(@RequestBody PatchCustomerDTO dto){
        ApiResponse response = new ApiResponse(true, this.customerService.patchCustomer(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
