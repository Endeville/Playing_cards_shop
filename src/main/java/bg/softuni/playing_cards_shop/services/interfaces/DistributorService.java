package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.views.DistributorDetailsDto;

public interface DistributorService {
    DistributorDetailsDto findDistributorById(Long id);
}
