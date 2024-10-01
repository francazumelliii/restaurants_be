package jac.project.restaurants.restaurants_be.menu;

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
@RequestMapping("/api/v1/menu")
public class MenuController {
    public final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<ApiResponse<List<MenuDTO>>> getAllRestaurantMenus(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true,  this.menuService.getAllRestaurantMenus(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<MenuDTO>>> getMenuById(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true, this.menuService.getMenuById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
