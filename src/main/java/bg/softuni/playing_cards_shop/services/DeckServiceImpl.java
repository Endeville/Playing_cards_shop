package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddDeckDto;
import bg.softuni.playing_cards_shop.models.dtos.EditDeckDto;
import bg.softuni.playing_cards_shop.models.dtos.rest.DeckTitleDto;
import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import bg.softuni.playing_cards_shop.models.views.DeckDetailsDto;
import bg.softuni.playing_cards_shop.models.views.DeckInfoDto;
import bg.softuni.playing_cards_shop.repositories.DeckRepository;
import bg.softuni.playing_cards_shop.services.interfaces.*;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DeckServiceImpl implements DeckService {

    private final DeckRepository deckRepository;
    private final PictureService pictureService;
    private final CreatorService creatorService;
    private final CategoryService categoryService;
    private final DistributorService distributorService;
    private final ModelMapper modelMapper;

    public DeckServiceImpl(DeckRepository deckRepository, PictureService pictureService, CreatorService creatorService, CategoryService categoryService, DistributorService distributorService, ModelMapper modelMapper) {
        this.deckRepository = deckRepository;
        this.pictureService = pictureService;
        this.creatorService = creatorService;
        this.categoryService = categoryService;
        this.distributorService = distributorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CatalogDeckDto> getDecksByKeyword(String search, String sort, String distributor, String creator) {
        return deckRepository.getDeckEntitiesByTitleDistributorOrCreatorContaining(search, Sort.by(sort), distributor, creator).stream()
                .map(e -> {
                    var deck = modelMapper.map(e, CatalogDeckDto.class);
                    deck.setPicture(this.pictureService.getPictureUrl(e.getPicture()));
                    return deck;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DeckDetailsDto findDeckDetailsById(long id) {
        var deck = deckRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DECK));

        var result = this.modelMapper.map(deck, DeckDetailsDto.class);
        result.setPicture(this.pictureService.getPictureUrl(deck.getPicture()));

        return result;
    }

    @Override
    public DeckEntity findDeckByTitle(String title) {
        return this.deckRepository
                .findDeckEntityByTitle(title)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DECK));
    }

    @Override
    public List<String> getAllDeckTitles() {
        return this.deckRepository.findAll().stream()
                .map(DeckEntity::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public void addDeck(AddDeckDto addDeckDto) throws IOException {
        var deck = this.modelMapper.map(addDeckDto, DeckEntity.class);

        var picture = this.pictureService.save(addDeckDto.getPicture());
        deck.setPicture(picture);

        var creator = this.creatorService.findByName(addDeckDto.getCreatorName());
        var distributor = this.distributorService.findDistributorByBrand(addDeckDto.getDistributorBrand());
        var categories = this.categoryService.findCategoriesByNames(addDeckDto.getCategories());


        deck.setCreator(creator)
                .setDistributor(distributor)
                .setCategories(new LinkedHashSet<>(categories))
                .setRecommendedPrice(-1);


        this.deckRepository.save(deck);
    }

    @Override
    public boolean titleExists(String title) {
        return this.deckRepository.existsByTitle(title);
    }

    @Override
    public void editDeck(Long id, EditDeckDto editDeckDto) throws IOException {
        var deck = this.deckRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DECK));

        if(editDeckDto.getPicture()==null || !Objects.requireNonNull(editDeckDto.getPicture().getOriginalFilename()).isBlank()){
            this.pictureService.deletePicture(deck.getPicture());

            var picture = this.pictureService.save(editDeckDto.getPicture());
            deck.setPicture(picture);
        }

        var creator = this.creatorService.findByName(editDeckDto.getCreatorName());
        var distributor = this.distributorService.findDistributorByBrand(editDeckDto.getDistributorBrand());
        var categories = this.categoryService.findCategoriesByNames(editDeckDto.getCategories());


        deck.setCreator(creator)
                .setDistributor(distributor)
                .setCategories(new LinkedHashSet<>(categories))
                .setTitle(editDeckDto.getTitle())
                .setCountryOfOrigin(editDeckDto.getCountryOfOrigin())
                .setDescription(editDeckDto.getDescription());


        this.deckRepository.save(deck);
    }

    @Override
    public DeckInfoDto findDeckInfoById(Long id) {
        var deck = deckRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DECK));

        var result = this.modelMapper.map(deck, DeckInfoDto.class);
        result.setPicture(this.pictureService.getPictureUrl(deck.getPicture()));

        return result;
    }

    @Transactional
    @Override
    public void updateRecommendedPrices() {
        var decks = this.deckRepository.findAll();
        for (DeckEntity deckEntity : decks) {
            deckEntity.setRecommendedPrice(calculateRecommendedPriceForDeck(deckEntity));
        }

        this.deckRepository.saveAll(decks);
    }

    @Override
    public void deleteDeck(DeckTitleDto deckTitleDto) {
        this.deckRepository.delete(
                this.deckRepository.findDeckEntityByTitle(deckTitleDto.getTitle())
                        .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DECK)));
    }


    private Integer calculateRecommendedPriceForDeck(DeckEntity deck) {
        return (int) deck
                .getOffers().stream()
                .filter(o -> o.getStatus().name().equals("AVAILABLE") || o.getStatus().name().equals("LIMITED"))
                .map(OfferEntity::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(-1);
    }
}
