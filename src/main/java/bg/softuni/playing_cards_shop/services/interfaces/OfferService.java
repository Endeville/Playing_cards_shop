package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.views.CatalogOfferDto;
import bg.softuni.playing_cards_shop.models.views.OfferDetailsDto;

import java.security.Principal;
import java.util.List;

public interface OfferService {
    void addOffer(AddOfferDto addOfferDto);

    List<CatalogOfferDto> getActiveOffers();

    OfferDetailsDto getOfferDetailsById(Long id);
}
