package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.WishlistItemEntity;
import bg.softuni.playing_cards_shop.repositories.WishlistItemRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.services.interfaces.WishlistItemService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class WishlistItemServiceImpl implements WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;
    private final DeckService deckService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public WishlistItemServiceImpl(WishlistItemRepository wishlistItemRepository, DeckService deckService, UserService userService, ModelMapper modelMapper) {
        this.wishlistItemRepository = wishlistItemRepository;
        this.deckService = deckService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public WishlistItemEntity like(UserDetails principal, String title) {
        var deck = this.deckService.findDeckByTitle(title);
        var user = this.userService.findUserByUsername(principal.getUsername());

        return this.wishlistItemRepository.save(new WishlistItemEntity()
                .setUser(user)
                .setDeck(deck));
    }
}
