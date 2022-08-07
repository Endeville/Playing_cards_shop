package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddDeckDto;
import bg.softuni.playing_cards_shop.models.entities.*;
import bg.softuni.playing_cards_shop.models.entities.enums.DeckCategory;
import bg.softuni.playing_cards_shop.repositories.*;
import bg.softuni.playing_cards_shop.services.cloudinary.CloudinaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeckServiceImplTest {

    @Mock
    private DeckServiceImpl deckService;

    @Mock
    private DeckRepository deckRepository;

    @Mock
    private PictureServiceImpl pictureService;

    @Mock
    private CreatorServiceImpl creatorService;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private DistributorServiceImpl distributorService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private CloudinaryService cloudinaryService;

    @Mock
    private CreatorRepository creatorRepository;

    @Mock
    private DistributorRepository distributorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setup(){
        pictureService=new PictureServiceImpl(pictureRepository, cloudinaryService, modelMapper);
        creatorService=new CreatorServiceImpl(creatorRepository, pictureService, modelMapper);
        categoryService = new CategoryServiceImpl(categoryRepository);
        distributorService=new DistributorServiceImpl(distributorRepository, pictureService, modelMapper);

        deckService=new DeckServiceImpl(deckRepository,pictureService,creatorService,
                categoryService,distributorService,modelMapper);
    }


    //todo: test add deck
//    @Test
//    public void testAddDeck() throws IOException {
//        var deck=getTestDeck();
//        var addDeck=new AddDeckDto()
//                .setTitle(deck.getTitle())
//                .setDescription(deck.getDescription())
//                .setCountryOfOrigin(deck.getCountryOfOrigin())
//                .setCreatorName(deck.getCreator().getName())
//                .setCategories(deck.getCategories().stream().map(c->c.getCategory().name()).collect(Collectors.toList()))
//                .setDistributorBrand(deck.getDistributor().getBrand())
//                .setPicture(new MockMultipartFile("text.txt","text.txt", "text/plain", new byte[0]));
//
//        lenient().when(modelMapper.map(addDeck, DeckEntity.class))
//                .thenReturn(deck);
//
//        when(pictureService.save(addDeck.getPicture()))
//                .thenReturn(new PictureEntity()
//                        .setUrl(addDeck.getPicture().getOriginalFilename())
//                        .setPublicId(addDeck.getPicture().getName()));
//
//        when(creatorService.findByName(addDeck.getCreatorName()))
//                .thenReturn(new CreatorEntity()
//                        .setName(addDeck.getCreatorName()));
//
//        when(distributorService.findDistributorByBrand(addDeck.getDistributorBrand()))
//                .thenReturn(new DistributorEntity()
//                        .setBrand(addDeck.getDistributorBrand()));
//
//        var categories=new ArrayList<CategoryEntity>();
//        for (int i = 0; i < addDeck.getCategories().size(); i++) {
//            categories.add(new CategoryEntity().setCategory(DeckCategory.valueOf(addDeck.getCategories().get(i))));
//        }
//
//        when(categoryService.findCategoriesByNames(addDeck.getCategories()))
//                .thenReturn(categories);
//
//        deckService.addDeck(addDeck);
//
//        assertEquals(addDeck.getTitle(), deck.getTitle());
//        assertEquals(addDeck.getCreatorName(), deck.getCreator().getName());
//        assertEquals(addDeck.getCategories(), deck.getCategories().stream().map(c->c.getCategory().name()).collect(Collectors.toList()));
//        assertEquals(addDeck.getDistributorBrand(), deck.getDistributor().getBrand());
//        assertEquals(addDeck.getPicture().getOriginalFilename(), deck.getPicture().getUrl());
//        assertEquals(addDeck.getCountryOfOrigin(), deck.getCountryOfOrigin());
//        assertEquals(addDeck.getDescription(), deck.getDescription());
//
//    }

    private DeckEntity getTestDeck(){
        var deck= new DeckEntity()
                .setTitle("Title")
                .setDescription("Description")
                .setCountryOfOrigin("Country")
                .setCategories(new HashSet<>(getTestCategories()))
                .setRecommendedPrice(-1)
                .setDistributor(getTestDistributor())
                .setCreator(getTestCreator())
                .setOffers(null)
                .setPicture(new PictureEntity().setPublicId("").setUrl(""));

        deck.getCreator().setDecks(Set.of(deck));
        deck.getDistributor().setDecks(Set.of(deck));

        return deck;
    }

    private List<CategoryEntity> getTestCategories(){
        return List.of(
                new CategoryEntity()
                        .setCategory(DeckCategory.GAME),
                new CategoryEntity()
                        .setCategory(DeckCategory.CARDISTRY)
        );
    }

    private DistributorEntity getTestDistributor(){
        return new DistributorEntity()
                .setBrand("Brand")
                .setDescription("Description")
                .setDecks(null)
                .setPicture(null)
                .setSiteUrl("http://site.com");
    }

    private CreatorEntity getTestCreator(){
        return new CreatorEntity()
                .setName("Creator")
                .setDescription("Description")
                .setDecks(null)
                .setPicture(null);
    }
}
