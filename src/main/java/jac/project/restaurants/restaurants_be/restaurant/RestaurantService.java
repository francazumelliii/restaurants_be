package jac.project.restaurants.restaurants_be.restaurant;

import jac.project.restaurants.restaurants_be.admin.AdminDTO;
import jac.project.restaurants.restaurants_be.company.CompanyDTO;
import jac.project.restaurants.restaurants_be.coords.CoordsDTO;
import jac.project.restaurants.restaurants_be.imgs.ImgsDTO;
import jac.project.restaurants.restaurants_be.reservation.ReservationService;
import jac.project.restaurants.restaurants_be.restaurant.DTOs.PatchRestaurantDTO;
import jac.project.restaurants.restaurants_be.village.Village;
import jac.project.restaurants.restaurants_be.village.VillageDTO;
import jac.project.restaurants.restaurants_be.village.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final VillageService villageService;
    private final ReservationService reservationService;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, VillageService villageService, ReservationService reservationService){
        this.restaurantRepository = restaurantRepository;
        this.villageService = villageService;
        this.reservationService = reservationService;
    }

    public List<RestaurantDTO> getAllRestaurants(){
        List<Restaurant> restaurants = this.restaurantRepository.findAll();
        return restaurants.stream().map(this::toRestaurantDTO).collect(Collectors.toList());
    }
    public Optional<RestaurantDTO> getRestaurantById(Integer id){
        if(id != null){
            Optional<Restaurant> restaurant = this.restaurantRepository.findById(id);
            return restaurant.map(this::toRestaurantDTO);
        }
        return Optional.empty();
    }

    public List<VillageDTO> getAllVillagesByCountyIdFromRestaurant(Integer restaurantId) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);

        if (restaurantOpt.isPresent()) {
            Village village = restaurantOpt.get().getVillage();
            Integer countyId = village.getCounty().getId();

            List<Village> villages = villageService.getVillagesByCountyId(countyId);

            return villages.stream()
                    .map(villageService::toVillageDTO)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    public List<RestaurantDTO> getRestaurantsBySearchText(String toSearch){
        if(toSearch == null){
            return Collections.emptyList();
        }else {
            return this.restaurantRepository.findRestaurantsBySearchText(toSearch)
                    .stream().map(this::toRestaurantDTO).collect(Collectors.toList());
        }
    }

    public List<RestaurantDTO> getAdminRestaurants(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        if(mail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }else{
            return this.restaurantRepository.getAdminRestaurants(mail).stream().map(this::toRestaurantDTO).collect(Collectors.toList());
        }
    }
    public List<RestaurantDTO> getFavoriteRestaurant(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();

        if(mail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }else{
            List<RestaurantDTO> favorites =  this.restaurantRepository.findFavoriteRestaurants(mail).stream().map(this::toRestaurantDTO).collect(Collectors.toList());
            if(favorites.size() > 0){
                return favorites;
            }else{
                return this.restaurantRepository.findAll().stream().map(this::toRestaurantDTO).collect(Collectors.toList());
            }
        }
    }

    public Integer getAvailableSeats(Integer restaurantId, String stringDate, Integer turnId) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (restaurantId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: restaurant_id");
        }
        if (turnId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: turn_id");
        }
        if (stringDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: date");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(stringDate, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Please use yyyy-MM-dd.");
        }

        return this.restaurantRepository.findAvailableSeats(restaurantId, date, turnId);
    }

    public Optional<Restaurant> getRestaurantEntity(Integer id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }else{
            return this.restaurantRepository.findById(id);
        }
    }

    public List<RestaurantDTO> getNearestRestaurants(Float latitude, Float longitude){
        if(latitude == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: latitude");
        }
        if(longitude == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: longitude");
        }else{
            return this.restaurantRepository.findNearestRestaurants(latitude, longitude)
                    .stream().map(this::toRestaurantDTO).collect(Collectors.toList());
        }
    }

    public void deleteRestaurant(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }

        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(id);
        Restaurant restaurant = optionalRestaurant.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Entity not found"));

        if (restaurant.getAdmins().stream().anyMatch(admin -> admin.getMail().equals(mail)) ||
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {

            this.restaurantRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not Authorized");
        }
    }



    public Optional<RestaurantDTO> patchRestaurant(Integer id, PatchRestaurantDTO dto){
        if(dto == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required body parameter: dto");
        }
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(id);
        Restaurant restaurant = optionalRestaurant.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Entity not found"));

        if(dto.getName() != null){
            restaurant.setName(dto.getName());
        }
        if(dto.getStreet() != null){
            restaurant.setStreet(dto.getStreet());
        }
        if(dto.getStreet_number() != null){
            restaurant.setStreetNumber(dto.getStreet_number());
        }
        if(dto.getLatitude() != null){
            restaurant.setLatitude(dto.getLatitude());
        }
        if(dto.getLongitude() != null){
            restaurant.setLongitude(dto.getLongitude());
        }
        if(dto.getDescription() != null){
            restaurant.setDescription(dto.getDescription());
        }
        if(dto.getBanner() != null){
            restaurant.setBanner(dto.getBanner());
        }
        if(dto.getVillage() != null){
            Optional<Village> optionalVillage = this.villageService.villageRepository.findById(dto.getVillage());
            Village village = optionalVillage.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Village Entity not found"));
            restaurant.setVillage(village);
        }
        Restaurant savedRestaurant = this.restaurantRepository.save(restaurant);
        return Optional.ofNullable(toRestaurantDTO(savedRestaurant));

    }

    public Integer getTotalChairs(Integer id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }
        return this.restaurantRepository.findTotalChairs(id);
    }



    public RestaurantDTO toRestaurantDTO(Restaurant restaurant){
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setRating(restaurant.getRating());
        dto.setStreet(restaurant.getStreet());
        dto.setStreet_number(restaurant.getStreetNumber());
        dto.setMax_chairs(restaurant.getMaxChairs());
        dto.setDescription(restaurant.getDescription());
        dto.setBanner(restaurant.getBanner());
        CompanyDTO companyDTO = new CompanyDTO(restaurant.getCompany().getId(), restaurant.getCompany().getName(),
                restaurant.getCompany().getVat(), restaurant.getCompany().getAddress(),
                restaurant.getCompany().getTelephone()
        );
        ImgsDTO imgDTO = new ImgsDTO(restaurant.getBanner());
        CoordsDTO coordsDTO = new CoordsDTO(restaurant.getLatitude(), restaurant.getLongitude(),
                restaurant.getVillage().getName(),
                restaurant.getVillage().getId(), restaurant.getVillage().getCounty().getName(),
                restaurant.getVillage().getCounty().getCode(), restaurant.getVillage().getCounty().getRegion().getName()
        );
        List<AdminDTO> adminDTOS = restaurant.getAdmins()
                .stream()
                .map(admin -> new AdminDTO(admin.getId(), admin.getName(), admin.getSurname(), admin.getMail()))
                .collect(Collectors.toList());

        dto.setCoords(coordsDTO);
        dto.setImg(imgDTO);
        dto.setCompany(companyDTO);
        dto.setAdmin(adminDTOS);
        return dto;
    }
}
