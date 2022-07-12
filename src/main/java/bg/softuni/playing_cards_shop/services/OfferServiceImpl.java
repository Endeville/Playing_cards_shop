package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.entities.ReviewEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus;
import bg.softuni.playing_cards_shop.models.views.CatalogOfferDto;
import bg.softuni.playing_cards_shop.repositories.OfferRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.services.security.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserService userService;
    private final DeckService deckService;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, UserService userService, DeckService deckService, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.deckService = deckService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(AddOfferDto addOfferDto) {
        var offer=this.modelMapper.map(addOfferDto, OfferEntity.class);

        offer.setStatus(OfferStatus.PENDING)
                .setSeller(userService.findUserByUsername(((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()))
                .setDeck(deckService.findDeckByTitle(addOfferDto.getTitle()))
                .setPrice(addOfferDto.getPrice())
                .setReviews(new HashSet<>())
                .setQuantity(addOfferDto.getQuantity());

        this.offerRepository.save(offer);
    }

    @Override
    public List<CatalogOfferDto> getActiveOffers() {
        return this.offerRepository.findOfferEntityByStatusApprovedOrLimited().stream()
                .map((o)->{
                    var offer=this.modelMapper.map(o, CatalogOfferDto.class);
                    offer.setTitle(o.getDeck().getTitle());

                    return offer;
                })
                .collect(Collectors.toList());
    }
}
