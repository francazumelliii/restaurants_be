package jac.project.restaurants.restaurants_be.imgs;

import jac.project.restaurants.restaurants_be.APIResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/imgs")
public class ImgsController {
    public final ImgsService imgsService;
    @Autowired
    public ImgsController(ImgsService imgsService){
        this.imgsService = imgsService;
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<ApiResponse<List<ImgsDTO>>> getAllImgsFromRestaurant(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true, this.imgsService.getAllImgsFromRestaurant(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
