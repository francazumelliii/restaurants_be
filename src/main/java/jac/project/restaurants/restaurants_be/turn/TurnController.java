package jac.project.restaurants.restaurants_be.turn;

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
@RequestMapping("/api/v1/turn")
public class TurnController {
    public final TurnService turnService;
    @Autowired
    public TurnController(TurnService turnService){
        this.turnService = turnService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TurnDTO>>> getAllTurns(){
        ApiResponse response = new ApiResponse(true, this.turnService.getAllTurns());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<TurnDTO>>> getTurnById(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true, this.turnService.getTurnById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
