package jac.project.restaurants.restaurants_be.authentication;

import jac.project.restaurants.restaurants_be.admin.AdminService;
import jac.project.restaurants.restaurants_be.admin.DTOs.PostAdminDTO;
import jac.project.restaurants.restaurants_be.customer.Customer;
import jac.project.restaurants.restaurants_be.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody Customer customer) {
        try {
            String token = customerService.registerCustomer(customer);
            return ResponseEntity.ok(new AuthenticationResponse(token, "customer"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new AuthenticationResponse(e.getReason(), null));
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/admin/signup")
    public ResponseEntity<AuthenticationResponse> adminSignup(@RequestBody PostAdminDTO admin) {
        try {
            String token = adminService.registerAdmin(admin);
            if (token != null) {
                System.out.println("Saved token: " + token);
                return ResponseEntity.ok(new AuthenticationResponse(token, "admin"));
            } else {
                System.out.println("Token is null");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Stampa lo stack trace per debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getMail(), authenticationRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getMail());
            final String jwt = jwtTokenUtil.generateToken(userDetails);

            String role = userDetailsService.getRole();
            return ResponseEntity.ok(new AuthenticationResponse(jwt, role));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during authentication");
        }
    }
}
