package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.WishlistItemEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import bg.softuni.playing_cards_shop.repositories.WishlistItemRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.services.interfaces.WishlistItemService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistItemServiceImpl implements WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;
    private final DeckService deckService;
    private final UserService userService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public WishlistItemServiceImpl(WishlistItemRepository wishlistItemRepository, DeckService deckService, UserService userService, PictureService pictureService, ModelMapper modelMapper) {
        this.wishlistItemRepository = wishlistItemRepository;
        this.deckService = deckService;
        this.userService = userService;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    @Override
    public WishlistItemEntity like(String title) {
        var deck = this.deckService.findDeckByTitle(title);
        var user = this.userService.getCurrentUser();

        return this.wishlistItemRepository.save(new WishlistItemEntity()
                .setUser(user)
                .setDeck(deck));
    }

    @Override
    public List<CatalogDeckDto> getCurrentUserWishlistByKeyword(UserDetails principal, String search, String sort) {

        return this.wishlistItemRepository
                .findWishlistItemEntitiesByUserUsernameAndDeckTitleContainingIgnoreCase(principal.getUsername(),
                        search,
                        Sort.by(sort))
                .stream()
                .map(w->{
                    var deck = modelMapper.map(w.getDeck(), CatalogDeckDto.class);
                    deck.setPicture(this.pictureService.getPictureUrl(w.getDeck().getPicture()));
                    return deck;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void dislike(String title) {
        var deck = this.deckService.findDeckByTitle(title);
        var user = this.userService.getCurrentUser();

        var item=this.wishlistItemRepository.findWishlistItemEntityByUserAndDeck(user,deck)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_WISHLIST_ITEM));
        this.wishlistItemRepository.delete(item);
    }

    @Override
    public boolean hasLiked(String username, String title) {
        return this.wishlistItemRepository.existsByUserUsernameAndDeckTitle(username, title);
    }
}
