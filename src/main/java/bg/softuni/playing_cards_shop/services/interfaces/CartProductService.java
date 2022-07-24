package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.rest.OfferIdDto;
import bg.softuni.playing_cards_shop.models.views.CartProductDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductEssentialsDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CartProductService {
    List<CartProductDto> getCartProductsByCustomer(String username);

    CartProductEssentialsDto addProduct(OfferIdDto offerIdDto, UserDetails principal);

    void deleteProduct(OfferIdDto offerIdDto, UserDetails principal);
}
