package jac.project.restaurants.restaurants_be.dish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishService {
    public final DishRepository dishRepository;
    @Autowired
    public DishService(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }


    public List<DishDTO> getAllDishes(){
        return this.dishRepository.findAll().stream().map(this::toDishDTO).collect(Collectors.toList());
    }
    public Optional<DishDTO> getDishById(Integer id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }else{
            return this.dishRepository.findById(id).map(this::toDishDTO);
        }
    }


    public DishDTO toDishDTO(Dish dish){
        boolean vegan = dish.isVegan() == 0  ? false : true;
        boolean lactoseFree = dish.isLactoseFree() == 0 ? false: true;

        DishDTO dto = new DishDTO(dish.getId(), dish.getName(), dish.getDescription(), dish.getIngredients(),
                vegan, lactoseFree, dish.getDishCategory().getName(), dish.getPrice());
        return dto;
    }
}
