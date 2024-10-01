package jac.project.restaurants.restaurants_be.restaurant;

import jac.project.restaurants.restaurants_be.APIResponse.ApiResponse;
import jac.project.restaurants.restaurants_be.restaurant.DTOs.PatchRestaurantDTO;
import jac.project.restaurants.restaurants_be.village.VillageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getAllRestaurants(){
        List<RestaurantDTO> restaurants = this.restaurantService.getAllRestaurants();
        ApiResponse response = new ApiResponse(true, restaurants);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<RestaurantDTO>>> getRestaurantById(@PathVariable Integer id){
            ApiResponse response = new ApiResponse(true, this.restaurantService.getRestaurantById(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/villages")
    public ResponseEntity<ApiResponse<List<VillageDTO>>> getVillagesFromRestaurantCounty(@PathVariable Integer id) {
        ApiResponse response = new ApiResponse(true, this.restaurantService.getAllVillagesByCountyIdFromRestaurant(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getRestaurantsBySearchText(@RequestParam(name = "toSearch", required = false) String toSearch){
        ApiResponse response = new ApiResponse(true, this.restaurantService.getRestaurantsBySearchText(toSearch));
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @GetMapping("/admin/me")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getAdminRestaurants(){
        ApiResponse response = new ApiResponse(true, this.restaurantService.getAdminRestaurants());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/customer/me/favorites")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getFavoriteRestaurants(){
        ApiResponse response = new ApiResponse(true, this.restaurantService.getFavoriteRestaurant());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}/tables")
    public ResponseEntity<ApiResponse<Integer>> getAvailableSeats(@PathVariable Integer id, @RequestParam(name = "turnId",required = true) Integer turnId, @RequestParam(name="date", required = true) String date){
        ApiResponse response = new ApiResponse(true,this.restaurantService.getAvailableSeats(id,date,turnId) );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/nearest")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getNearestRestaurants(@RequestParam(name = "lat") Float latitude,@RequestParam(name = "lon") Float longitude){
        ApiResponse response = new ApiResponse(true,this.restaurantService.getNearestRestaurants(latitude,longitude));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRestaurant(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true, null);
        this.restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<RestaurantDTO>>> patchRestaurant(@PathVariable Integer id, @RequestBody PatchRestaurantDTO dto){
//        return this.restaurantService.patchRestaurant(id, dto);
        ApiResponse<Optional<RestaurantDTO>> response = new ApiResponse<>(true, this.restaurantService.patchRestaurant(id,dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
