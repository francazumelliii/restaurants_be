package jac.project.restaurants.restaurants_be.village;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class VillageController {
    private final VillageService villageService;
    @Autowired
    public VillageController(VillageService villageService){
        this.villageService = villageService;
    }


}
