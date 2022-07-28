package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.WishlistItemEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface WishlistItemService {
    WishlistItemEntity like(UserDetails principal, String title);

    List<CatalogDeckDto> getCurrentUserWishlist(UserDetails principal);

    void dislike(UserDetails principal, String title);
}