package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.dtos.AddReviewDto;
import bg.softuni.playing_cards_shop.models.dtos.EditOfferDto;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogOfferDto;
import bg.softuni.playing_cards_shop.models.views.OfferDetailsDto;
import bg.softuni.playing_cards_shop.models.views.OfferInfoDto;
import bg.softuni.playing_cards_shop.models.views.ReviewDetailsDto;

import java.io.IOException;
import java.util.List;

public interface OfferService {
    void addOffer(AddOfferDto addOfferDto) throws IOException;

    List<CatalogOfferDto> getActiveOffers();

    OfferDetailsDto getOfferDetailsById(Long id);

    OfferEntity findOfferById(Long id);

    void editOffer(Long id, EditOfferDto offer) throws IOException;

    OfferInfoDto getOfferInfoById(Long id);

    List<ReviewDetailsDto> findReviewsByOfferId(Long id);

    void addReviewToOfferById(Long id, AddReviewDto review);
}
