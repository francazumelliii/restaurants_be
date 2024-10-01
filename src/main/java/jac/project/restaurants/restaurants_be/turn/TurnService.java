package jac.project.restaurants.restaurants_be.turn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnService {
    public final TurnRepository turnRepository;
    @Autowired
    public TurnService(TurnRepository turnRepository){
        this.turnRepository = turnRepository;
    }

    public List<TurnDTO> getAllTurns(){
        return this.turnRepository.findAll().stream().map(this::toTurnDto).collect(Collectors.toList());
    }
    public Optional<TurnDTO> getTurnById(Integer id ){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }else{
            return this.turnRepository.findById(id).map(this::toTurnDto);
        }
    }

    public Optional<Turn> getTurnEntity(Integer id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }else{
            return this.turnRepository.findById(id);
        }
    }

    public TurnDTO toTurnDto(Turn turn){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String start = turn.getStart().format(formatter);
        String end = turn.getEnd().format(formatter);
        TurnDTO dto = new TurnDTO(turn.getId(), start, end);
        return dto;
    }
}
