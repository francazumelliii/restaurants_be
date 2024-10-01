package jac.project.restaurants.restaurants_be.admin;

import jac.project.restaurants.restaurants_be.admin.DTOs.PatchAdminDTO;
import jac.project.restaurants.restaurants_be.admin.DTOs.PostAdminDTO;
import jac.project.restaurants.restaurants_be.authentication.CustomUserDetailsService;
import jac.project.restaurants.restaurants_be.authentication.JwtTokenUtil;
import jac.project.restaurants.restaurants_be.restaurant.Restaurant;
import jac.project.restaurants.restaurants_be.restaurant.RestaurantRepository;
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
public class AdminService {

    private final AdminRepository adminRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AdminService(AdminRepository adminRepository, RestaurantRepository restaurantRepository,
                        PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, CustomUserDetailsService userDetailsService) {
        this.adminRepository = adminRepository;
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public String getAdminList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        if (mail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            return this.adminRepository.findAdminList(mail);
        }
    }

    public Optional<AdminDTO> getAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        if (mail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            return this.adminRepository.findByMail(mail).map(this::toAdminDto);
        }
    }

    public Optional<AdminDTO> patchAdmin(PatchAdminDTO dto) {
        if (dto.getMail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: mail");
        }
        Optional<Admin> optionalAdmin = this.adminRepository.findByMail(dto.getMail());
        Admin admin = optionalAdmin.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Entity not found"));

        if (dto.getName() != null) {
            admin.setName(dto.getName());
        }
        if (dto.getSurname() != null) {
            admin.setSurname(dto.getSurname());
        }
        if (dto.getRestaurant_id() != null) {
            Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(dto.getRestaurant_id());
            Restaurant restaurant = optionalRestaurant.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Entity not found"));
            admin.setRestaurant(restaurant);
        }

        return Optional.ofNullable(toAdminDto(this.adminRepository.save(admin)));
    }

    public String registerAdmin(PostAdminDTO dto) {
        if(dto.getMail() != null){
            Optional<Admin> optionalAdmin = this.adminRepository.findByMail(dto.getMail());
            if(optionalAdmin.isPresent()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User with same mail is already present");
            }
        }
        if(dto.getRestaurant_id() != null){
            Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(dto.getRestaurant_id());
            Restaurant restaurant = optionalRestaurant.orElseThrow(() ->new  ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Entity not found"));

        Admin admin = new Admin();
            admin.setName(dto.getName());
            admin.setSurname(dto.getSurname());
            admin.setMail(dto.getMail());
            admin.setPassword(dto.getPassword());
            admin.setRestaurant(restaurant);
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepository.save(admin);

            UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getMail());
            return jwtTokenUtil.generateToken(userDetails);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Entity not found");
        }

    }

    public AdminDTO toAdminDto(Admin admin) {
        return new AdminDTO(admin.getId(), admin.getName(), admin.getSurname(), admin.getMail());
    }
}
