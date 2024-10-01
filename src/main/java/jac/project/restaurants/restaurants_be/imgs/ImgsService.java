package jac.project.restaurants.restaurants_be.imgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImgsService {
    public final ImgsRepository imgsRepository;
    @Autowired
    public ImgsService(ImgsRepository imgsRepository){
        this.imgsRepository = imgsRepository;
    }

    public ImgsDTO toImgsDTO(Imgs img){
        return new ImgsDTO(img.getPath());

    }
    public List<ImgsDTO> getAllImgsFromRestaurant(Integer id){
        return this.imgsRepository.findAllImgsFromRestaurantId(id).stream().map(this::toImgsDTO).collect(Collectors.toList());
    }
}
