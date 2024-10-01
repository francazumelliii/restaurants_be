package jac.project.restaurants.restaurants_be.reservation;

import jac.project.restaurants.restaurants_be.APIResponse.ApiResponse;
import jac.project.restaurants.restaurants_be.reservation.DTOModels.AvailableSeatsDTO;
import jac.project.restaurants.restaurants_be.reservation.DTOModels.PatchReservationDTO;
import jac.project.restaurants.restaurants_be.reservation.DTOModels.PostReservationDTO;
import jac.project.restaurants.restaurants_be.stats.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    public final ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/customer/me")
    public ResponseEntity<ApiResponse<List<ReservationDTO>>> getAllCustomerReservations(){
        ApiResponse response = new ApiResponse(true, this.reservationService.getAllCustomerReservations());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<ApiResponse<List<ReservationDTO>>> getRestaurantReservations(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true,  this.reservationService.getRestaurantReservations(id));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<ReservationDTO>> postReservation(@RequestBody PostReservationDTO dto){
        ApiResponse response = new ApiResponse(true, this.reservationService.postReservation(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservationDTO>> patchReservation(@PathVariable Integer id, @RequestBody PatchReservationDTO dto){
        ApiResponse response = new ApiResponse(true, this.reservationService.patchReservation(id, dto));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<ReservationDTO>>> deleteReservation(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true, this.reservationService.deleteReservation(id));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping("/restaurant/{id}/stats")
    public ResponseEntity<ApiResponse<List<Stats>>> getDayTimeStats(@PathVariable Integer id){

        List<Stats> stats = this.reservationService.getDayTimesStats(id);
        ApiResponse response = new ApiResponse<>(true, stats);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping("/restaurant/{id}/seats")
    public ResponseEntity<ApiResponse<AvailableSeatsDTO>> getActuallyAvailableSeats(@PathVariable Integer id){
        ApiResponse response = new ApiResponse(true, this.reservationService.getActuallyAvailableSeats(id));
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
