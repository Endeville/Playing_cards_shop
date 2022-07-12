package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.constants.GlobalConstants;
import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import bg.softuni.playing_cards_shop.models.views.DeckDetailsDto;
import bg.softuni.playing_cards_shop.repositories.DeckRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.web.exceptions.ItemNotCreatedException;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_DECK;

@Service
public class DeckServiceImpl implements DeckService {

    private final DeckRepository deckRepository;
    private final ModelMapper modelMapper;

    public DeckServiceImpl(DeckRepository deckRepository, ModelMapper modelMapper) {
        this.deckRepository = deckRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CatalogDeckDto> getApprovedDecks() {
        return deckRepository.getDeckEntityByApproved(true).stream()
                .map(e->modelMapper.map(e, CatalogDeckDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DeckDetailsDto findDeckDetailsById(long id) {
        var deck=deckRepository.getDeckEntityByIdAndApprovedIsTrue(id)
                .orElse(null);
        return this.modelMapper.map(deck, DeckDetailsDto.class);
    }

    @Override
    public Integer getRecommendedPriceForDeckWithId(long id) {
        return (int) this.deckRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DECK))
                .getOffers().stream()
                .filter(o->o.getStatus().name().equals("APPROVED") || o.getStatus().name().equals("LIMITED"))
                .map(OfferEntity::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(-1);
    }

    @Override
    public DeckEntity findDeckByTitle(String title) {
        return this.deckRepository
                .findDeckEntityByTitle(title)
                .orElseThrow(()-> new ItemNotCreatedException(OBJECT_NAME_DECK));
    }
}
