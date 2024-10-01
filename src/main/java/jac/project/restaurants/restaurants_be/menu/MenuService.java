package jac.project.restaurants.restaurants_be.menu;

import jac.project.restaurants.restaurants_be.dish.DishDTO;
import jac.project.restaurants.restaurants_be.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {
    public final MenuRepository menuRepository;
    public final DishService dishService;

    @Autowired
    public MenuService(MenuRepository menuRepository, DishService dishService) {
        this.menuRepository = menuRepository;
        this.dishService = dishService;
    }

    public List<MenuDTO> getAllRestaurantMenus(Integer id) {
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }
        return this.menuRepository.findAllByRestaurant(id).stream().map(this::toMenuDto).collect(Collectors.toList());
    }
    public Optional<MenuDTO> getMenuById(Integer id ){
        if (id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter: id");
        }else{
            return this.menuRepository.findById(id).map(this::toMenuDto);
        }
    }

    public MenuDTO toMenuDto(Menu menu) {
        // Convert each Dish to a DishDTO using the dishService's toDishDTO method
        List<DishDTO> dishes = menu.getDishes().stream()
                .map(dish -> dishService.toDishDTO(dish))
                .collect(Collectors.toList());

        // Create and return a new MenuDTO with the converted dishes list
        return new MenuDTO(menu.getId(), menu.getName(), menu.getDescription(), menu.getCategory(), dishes);
    }


}