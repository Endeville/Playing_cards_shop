package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.WishlistItemEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface WishlistItemService {

    String OBJECT_NAME_WISHLIST_ITEM="wishlist item";

    WishlistItemEntity like(String title);

    List<CatalogDeckDto> getCurrentUserWishlistByKeyword(UserDetails principal, String search, String sort);

    void dislike(String title);
}