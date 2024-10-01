package jac.project.restaurants.restaurants_be.reservation;

import jac.project.restaurants.restaurants_be.customer.Customer;
import jac.project.restaurants.restaurants_be.customer.CustomerService;
import jac.project.restaurants.restaurants_be.reservation.DTOModels.AvailableSeatsDTO;
import jac.project.restaurants.restaurants_be.reservation.DTOModels.PatchReservationDTO;
import jac.project.restaurants.restaurants_be.reservation.DTOModels.PostReservationDTO;
import jac.project.restaurants.restaurants_be.restaurant.Restaurant;
import jac.project.restaurants.restaurants_be.restaurant.RestaurantService;
import jac.project.restaurants.restaurants_be.stats.Stats;
import jac.project.restaurants.restaurants_be.turn.Turn;
import jac.project.restaurants.restaurants_be.turn.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    public ReservationRepository reservationRepository;
    public CustomerService customerService;
    public RestaurantService restaurantService;
    public TurnService turnService;
    @Autowired
    @Lazy
    public ReservationService(ReservationRepository reservationRepository, CustomerService customerService, RestaurantService restaurantService, TurnService turnService){
        this.restaurantService = restaurantService;
        this.customerService = customerService;
        this.reservationRepository = reservationRepository;
        this.turnService = turnService;
    }

    public List<ReservationDTO> getAllCustomerReservations(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        if(mail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }else{
            return this.reservationRepository.findReservationsByCustomerMail(mail).stream().map(this::toReservationDTO).collect(Collectors.toList());
        }
    }

    public List<ReservationDTO> getRestaurantReservations(Integer id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }else{
            return this.reservationRepository.getRestaurantReservations(id).stream().map(this::toReservationDTO).collect(Collectors.toList());
        }
    }

    public ReservationDTO postReservation(PostReservationDTO dto){
        if(dto == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required body request parameters");
        }

        Reservation reservation = new Reservation();
        if(dto.getMail() != null){
            reservation.setMail(dto.getMail());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: mail");
        }
        if(dto.getDate() != null){
            reservation.setDate(LocalDate.parse(dto.getDate()));
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: date {yyyy-MM-dd}");
        }
        if(dto.getQuantity() != null){
            reservation.setQuantity(dto.getQuantity());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: quantity {int}");
        }
        if(dto.getTurn_id() != null){
            Optional<Turn> optionalTurn = this.turnService.getTurnEntity(dto.getTurn_id());
            Turn turn = optionalTurn.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turn Entity not found"));
            reservation.setTurn(turn);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: turn_id");
        }
        if(dto.getRestaurant_id() != null){
            Optional<Restaurant> optionalRestaurant = this.restaurantService.getRestaurantEntity(dto.getRestaurant_id());
            Restaurant restaurant = optionalRestaurant.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Entity not found"));
            reservation.setRestaurant(restaurant);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: restaurant_id");
        }
        if(dto.getCustomer_mail() != null){
            Optional<Customer> optionalCustomer = this.customerService.getCustomerEntity(dto.getCustomer_mail());
            Customer customer = optionalCustomer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Entity not found"));
            reservation.setCustomer(customer);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: customer_mail");
        }

        reservation.setConfirmed(1);
        return toReservationDTO(this.reservationRepository.save(reservation));


    }

    public ReservationDTO patchReservation(Integer id, PatchReservationDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required body request parameters");
        }

        // Fetch the existing reservation
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (dto.getDate() != null) {
            reservation.setDate(LocalDate.parse(dto.getDate()));
        }
        if (dto.getMail() != null) {
            reservation.setMail(dto.getMail());
        }
        if (dto.getQuantity() != null) {
            reservation.setQuantity(dto.getQuantity());
        }
        if (dto.getTurn_id() != null) {
            Turn turn = turnService.getTurnEntity(dto.getTurn_id())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turn not found"));
            reservation.setTurn(turn);
        }

        Reservation updatedReservation = reservationRepository.save(reservation);
        return toReservationDTO(updatedReservation);
    }

    public Optional<ReservationDTO> deleteReservation(Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }
        Optional<Reservation> optionalReservation = this.reservationRepository.findById(id);
        Reservation reservation = optionalReservation.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation Entity not found"));
        if(reservation.getCustomer().getMail().equals(mail) || authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            reservation.setConfirmed(0);
            return Optional.ofNullable(toReservationDTO(this.reservationRepository.save(reservation)));
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not Authorized");
        }

    }

    public List<Stats> getDayTimesStats(Integer id){
        Date input = new Date();
        LocalDate date = LocalDate.ofInstant(input.toInstant(), ZoneId.systemDefault());

        List<Stats> stats = new ArrayList<Stats>();
        System.out.println("TODAY" + date);

        //stats of morning
        Integer morningStats =  this.reservationRepository.findDayTimeStats(id, LocalTime.parse("08:00:00"),LocalTime.parse("13:00:00"), date);
        Stats morning = new Stats("Morning", morningStats);
        stats.add(morning);

        Integer afternoonStats = this.reservationRepository.findDayTimeStats(id, LocalTime.parse("13:00:00"), LocalTime.parse("18:00:00"), date);
        Stats afternoon = new Stats("Afternoon", afternoonStats);
        stats.add(afternoon);

        Integer eveningStats = this.reservationRepository.findDayTimeStats(id, LocalTime.parse("18:00:00"), LocalTime.parse("21:00:00"), date);
        Stats evening = new Stats("Evening", eveningStats);
        stats.add(evening);

        return stats;

    }

    public AvailableSeatsDTO getActuallyAvailableSeats(Integer id){
        Date input = new Date();
        LocalDate date = LocalDate.ofInstant(input.toInstant(), ZoneId.systemDefault());
        LocalTime currentTime = LocalTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalTime startOfHour = currentTime.withMinute(0).withSecond(0).withNano(0);
        LocalTime endOfHour = startOfHour.plusHours(1);

        AvailableSeatsDTO obj = new AvailableSeatsDTO();
        Integer reservedNow = this.reservationRepository.findTotalReservedNow(id, date, startOfHour, endOfHour);
        Integer totalChairs = this.restaurantService.getTotalChairs(id);
        if(reservedNow != null && totalChairs != null) {
            obj.setAvailable(totalChairs-reservedNow);
            obj.setTotal((totalChairs));
        }else{
            obj.setTotal(totalChairs);
            obj.setAvailable(totalChairs);
        }
        return obj;


    }





    // FROM ENTITY TO DTO
    public ReservationDTO toReservationDTO(Reservation reservation){
        ReservationDTO dto =  new ReservationDTO();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");

        String date = reservation.getDate().format(dateFormatter);
        String start = reservation.getTurn().getStart().format(timeFormatter);
        String end = reservation.getTurn().getEnd().format(timeFormatter);
        dto.setRestaurant(restaurantService.toRestaurantDTO(reservation.getRestaurant()));
        dto.setCustomer(customerService.toCustomerDTO(reservation.getCustomer()));

        dto.setId(reservation.getId());
        dto.setDate(date);
        dto.setQuantity(reservation.getQuantity());
        dto.setConfirmed(reservation.getConfirmed());
        dto.setMail(reservation.getMail());
        dto.setStart_time(start);
        dto.setEnd_time(end);


        return dto;
    }


}
