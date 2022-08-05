package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.rest.OfferIdDto;
import bg.softuni.playing_cards_shop.models.entities.CartProductEntity;
import bg.softuni.playing_cards_shop.models.views.CartProductDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductEssentialsDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductPriceQuantityDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface CartProductService {

    String OBJECT_NAME_CART_PRODUCT="cart product";

    List<CartProductDto> getCartProductsByCustomer(String username);

    CartProductEssentialsDto addProduct(OfferIdDto offerIdDto, UserDetails principal);

    void deleteProduct(OfferIdDto offerIdDto);

    CartProductPriceQuantityDto changeQuantity(Long offerId, int i);

    void deleteCartProducts(Set<CartProductEntity> cart);

    void deleteProduct(CartProductEntity cartProduct);

    boolean hasCarted(String name, Long id);
}
