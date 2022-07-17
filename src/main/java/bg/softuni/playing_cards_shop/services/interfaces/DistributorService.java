package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddDistributorDto;
import bg.softuni.playing_cards_shop.models.entities.DistributorEntity;
import bg.softuni.playing_cards_shop.models.views.DistributorDetailsDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DistributorService {
    DistributorDetailsDto findDistributorById(Long id);

    void addDistributor(AddDistributorDto distributorDto) throws IOException;

    List<String> getDistributorsNames();

    Optional<DistributorEntity> findDistributorByBrand(String distributorBrand);
}
