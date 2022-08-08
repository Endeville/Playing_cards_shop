package bg.softuni.playing_cards_shop.utils;

import bg.softuni.playing_cards_shop.models.entities.*;
import bg.softuni.playing_cards_shop.models.entities.enums.DeckCategory;
import bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Component
public class TestDataUtils {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public static boolean isSetUp=false;

    public static OfferEntity testOffer1, testOffer2;
    public static DeckEntity testDeck1, testDeck2;
    public static DistributorEntity testDistributor;
    public static CreatorEntity testCreator;
    public static PictureEntity testPicture;
    public static UserEntity testClient, testAdmin;
    public static UserEntity testReviewer1, testReviewer2;
    public static ReviewEntity testReview1, testReview2, testReview3;
    public static MockMultipartFile emptyFile=new MockMultipartFile("test.txt", "","text/plain", new byte[0]);

    public void init(){
        if(!isSetUp){
            initRoles();
            initPictures();

            testClient=createTestClient("TestClient1", "test1@email.com");
            testReviewer1=createTestClient("TestReviewer1", "test2@email.com");
            testReviewer2=createTestClient("TestReviewer2", "test3@email.com");
            testAdmin=createTestAdmin("TestAdmin1", "test4@email.com");

            testPicture=getTestPicture();
//            testDistributor=getTestDistributor("Brand1", testPicture, null);
            testCreator=createTestCreator("testCreator", null, testPicture);
//
//            testReview1=getTestReview(testReviewer1);
//            testReview2=getTestReview(testReviewer2);
//            testReview3=getTestReview(testReviewer1);
//
//            testDeck1=createTestDeck("Title1", testPicture, testDistributor, testCreator, null);
//            testDeck2=createTestDeck("Title2", testPicture, testDistributor, testCreator, null);
//
//            testOffer1=getTestOffer(testDeck1, testClient, testPicture, Set.of(testReview1, testReview2));
//            testOffer2=getTestOffer(testDeck2, testAdmin, testPicture, Set.of(testReview3));

            isSetUp=true;
        }
    }


    public void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.CLIENT);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public void initCategories(){
        if(categoryRepository.count()==0){
            for (DeckCategory value : DeckCategory.values()) {
                categoryRepository.save(new CategoryEntity().setCategory(value));
            }
        }
    }

    public void initPictures(){
        if(pictureRepository.count()==0){
            pictureRepository.save(new PictureEntity().setUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg")
                    .setPublicId("default_creator_wgjltr.jpg"));
            pictureRepository.save(new PictureEntity().setUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png")
                    .setPublicId("default_distributor_ogp1ju.png"));
        }
    }

    public UserEntity createTestAdmin(String username, String email) {
        initRoles();

        var user = new UserEntity()
                .setUsername(username)
                .setPassword("123456")
                .setEmail(email)
                .setRating((short) 5)
                .setRole(userRoleRepository.findUserRoleEntityByRole(UserRole.ADMIN).get());

        return userRepository.save(user);
    }

    public UserEntity createTestClient(String username, String email) {
        initRoles();

        var user = new UserEntity()
                .setUsername(username)
                .setPassword("123456")
                .setEmail(email)
                .setRating((short) 4)
                .setRole(userRoleRepository.findUserRoleEntityByRole(UserRole.CLIENT).get());

        return userRepository.save(user);
    }

    public CreatorEntity createTestCreator(String name, Set<DeckEntity> decks, PictureEntity picture){
        var creator= new CreatorEntity()
                .setName(name)
                .setDescription("TestDescription")
                .setDecks(decks)
                .setPicture(picture);

        return this.creatorRepository.save(creator);
    }

    public DeckEntity createTestDeck(String title, PictureEntity picture, DistributorEntity distributor,
                                     CreatorEntity creator, Set<OfferEntity> offers){

        initCategories();

        var deck=new DeckEntity()
                .setTitle(title)
                .setDescription("testDescription")
                .setRecommendedPrice(12)
                .setPicture(picture)
                .setCountryOfOrigin("USA")
                .setDistributor(distributor)
                .setCreator(creator)
                .setCategories(Set.of(new CategoryEntity().setCategory(DeckCategory.GAFFED), new CategoryEntity().setCategory(DeckCategory.POKER)))
                .setOffers(offers);

        return deckRepository.save(deck);
    }

    public DistributorEntity getTestDistributor(String brand, PictureEntity picture, Set<DeckEntity> decks){
        var distributor=new DistributorEntity()
                .setBrand(brand)
                .setSiteUrl("https://site/com")
                .setDescription("TestDescription")
                .setPicture(picture)
                .setDecks(decks);

        return distributorRepository.save(distributor);
    }

    public PictureEntity getTestPicture(){
        var picture=new PictureEntity()
                .setUrl("TestUrl.com")
                .setPublicId("TestPublicId.com");

        return pictureRepository.save(picture);
    }

    public OfferEntity getTestOffer(DeckEntity deck, UserEntity seller, PictureEntity picture,
                                     Set<ReviewEntity> reviews){
        var offer=new OfferEntity()
                .setDeck(deck)
                .setSeller(seller)
                .setPrice(BigDecimal.TEN)
                .setQuantity(4)
                .setDescription("TestDescription")
                .setStatus(OfferStatus.AVAILABLE)
                .setPicture(picture)
                .setReviews(reviews);

        return offerRepository.save(offer);
    }

    public ReviewEntity getTestReview(UserEntity creator){
        var review=new ReviewEntity()
                .setReview("TestReviews")
                .setCreated(Instant.now())
                .setRating((short)3)
                .setCreator(creator);

        return reviewRepository.save(review);
    }

//    public void cleanUp() {
//        distributorRepository.deleteAll();
//        creatorRepository.deleteAll();
//        categoryRepository.deleteAll();
//        userRepository.deleteAll();
//        userRoleRepository.deleteAll();
//        addressRepository.deleteAll();
//        pictureRepository.deleteAll();
//        reviewRepository.deleteAll();
//        offerRepository.deleteAll();
//        deckRepository.deleteAll();
//    }

}
