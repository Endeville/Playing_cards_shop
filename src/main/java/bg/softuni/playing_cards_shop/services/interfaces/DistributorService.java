package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddDistributorDto;
import bg.softuni.playing_cards_shop.models.views.DistributorDetailsDto;

import java.io.IOException;

public interface DistributorService {
    DistributorDetailsDto findDistributorById(Long id);

    void addDistributor(AddDistributorDto distributorDto) throws IOException;
}
