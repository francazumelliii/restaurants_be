package jac.project.restaurants.restaurants_be.dish;

import jac.project.restaurants.restaurants_be.APIResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dish")
public class DishController {
    public final DishService dishService;
    @Autowired
    public DishController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getAllDishes(){
        ApiResponse response = new ApiResponse(true, this.dishService.getAllDishes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<DishDTO>>> getDishById(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true, this.dishService.getDishById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
