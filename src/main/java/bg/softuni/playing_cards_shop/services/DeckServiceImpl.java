package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddDeckDto;
import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import bg.softuni.playing_cards_shop.models.views.DeckDetailsDto;
import bg.softuni.playing_cards_shop.repositories.DeckRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
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
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public DeckServiceImpl(DeckRepository deckRepository, PictureService pictureService, ModelMapper modelMapper) {
        this.deckRepository = deckRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CatalogDeckDto> getApprovedDecks() {
        return deckRepository.getDeckEntityByApproved(true).stream()
                .map(e->{
                   var deck= modelMapper.map(e, CatalogDeckDto.class);
                   deck.setPictures(this.pictureService.getPicturesUrls(e.getPictures()));
                   return deck;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DeckDetailsDto findDeckDetailsById(long id) {
        var deck=deckRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_DECK));

        var result= this.modelMapper.map(deck, DeckDetailsDto.class);
        result.setPictures(this.pictureService.getPicturesUrls(deck.getPictures()));

        return result;
    }

    @Override
    public Integer getRecommendedPriceForDeckWithId(long id) {
        return (int) this.deckRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DECK))
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

    @Override
    public List<String> getAllDeckTitles() {
        return this.deckRepository.getDeckEntityByApproved(true).stream()
                .map(DeckEntity::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public void addDeck(AddDeckDto addDeckDto) {
        //todo: check if distributor and creator are valid -> map dto -> upload pictures -> persist entity


    }

    @Override
    public boolean titleExists(String title) {
        return this.deckRepository.existsByTitle(title);
    }
}
