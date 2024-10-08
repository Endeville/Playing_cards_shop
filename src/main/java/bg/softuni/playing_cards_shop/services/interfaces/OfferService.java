package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.dtos.AddReviewDto;
import bg.softuni.playing_cards_shop.models.dtos.EditOfferDto;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogOfferDto;
import bg.softuni.playing_cards_shop.models.views.OfferDetailsDto;
import bg.softuni.playing_cards_shop.models.views.OfferInfoDto;
import bg.softuni.playing_cards_shop.models.views.ReviewDetailsDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;

public interface OfferService {

    String OBJECT_NAME_OFFER="offer";

    void addOffer(AddOfferDto addOfferDto) throws IOException;

    List<CatalogOfferDto> getActiveOffersByKeyword(String search, String seller, String sort);

    OfferDetailsDto getOfferDetailsById(Long id);

    OfferEntity findOfferById(Long id);

    void editOffer(Long id, EditOfferDto offer) throws IOException;

    OfferInfoDto getOfferInfoById(Long id);

    List<ReviewDetailsDto> findReviewsByOfferId(Long id);

    void addReviewToOfferById(Long id, AddReviewDto review);

    boolean offerHasBeenRatedByUser(Long id, UserDetails principal);

    void deleteOffer(Long id);

    int decreaseQuantity(OfferEntity offer, int quantity);

    boolean isOwner(String name, Long id);

    boolean checkAvailability(Long id);
}
