package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.WishlistItemEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface WishlistItemService {
    WishlistItemEntity like(UserDetails principal, String title);
}
