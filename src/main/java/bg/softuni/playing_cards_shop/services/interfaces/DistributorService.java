package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddDistributorDto;
import bg.softuni.playing_cards_shop.models.entities.DistributorEntity;
import bg.softuni.playing_cards_shop.models.views.DistributorDetailsDto;

import java.io.IOException;
import java.util.List;

public interface DistributorService {

    void addDistributor(AddDistributorDto distributorDto) throws IOException;

    List<String> getDistributorsNames();

    DistributorEntity findDistributorByBrand(String distributorBrand);

    DistributorDetailsDto findDistributorDetailsByBrand(String brand);

    boolean brandExists(String brand);
}
