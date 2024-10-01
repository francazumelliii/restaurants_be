package jac.project.restaurants.restaurants_be.village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillageService {
    public final VillageRepository villageRepository;
    @Autowired
    public VillageService(VillageRepository villageRepository){
        this.villageRepository = villageRepository;
    }

    public List<Village> getVillagesByCountyId(Integer id){
        return this.villageRepository.getVillagesByCountyId(id);
    }
    public VillageDTO toVillageDTO(Village village){
        VillageDTO dto = new VillageDTO(village.getId(), village.getName(), village.getCadastalCode(),
                village.getLatitude(), village.getLongitude());
        return dto;
    }

}
