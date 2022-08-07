package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.entities.*;
import bg.softuni.playing_cards_shop.utils.TestDataUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private OfferEntity testOffer1, testOffer2;
    private DeckEntity testDeck1, testDeck2;
    private DistributorEntity testDistributor;
    private CreatorEntity testCreator;
    private PictureEntity testPicture;
    private UserEntity testClient, testAdmin;
    private UserEntity testReviewer1, testReviewer2;
    private ReviewEntity testReview1, testReview2, testReview3;

    @BeforeEach
    void setup(){
        testClient=testDataUtils.createTestClient("TestClient1", "test1@email.com");
        testReviewer1=testDataUtils.createTestClient("TestReviewer1", "test2@email.com");
        testReviewer2=testDataUtils.createTestClient("TestReviewer2", "test3@email.com");
        testAdmin=testDataUtils.createTestAdmin("TestAdmin1", "test4@email.com");

        testPicture=testDataUtils.getTestPicture();
        testDistributor=testDataUtils.getTestDistributor("Brand1", testPicture, null);
        testCreator=testDataUtils.createTestCreator("Creatro1", null, testPicture);

        testReview1=testDataUtils.getTestReview(testReviewer1);
        testReview2=testDataUtils.getTestReview(testReviewer2);
        testReview3=testDataUtils.getTestReview(testReviewer1);

        testDeck1=testDataUtils.createTestDeck("Title1", testPicture, testDistributor, testCreator, null);
        testDeck2=testDataUtils.createTestDeck("Title2", testPicture, testDistributor, testCreator, null);

        testOffer1=testDataUtils.getTestOffer(testDeck1, testClient, testPicture, Set.of(testReview1, testReview2));
        testOffer2=testDataUtils.getTestOffer(testDeck2, testAdmin, testPicture, Set.of(testReview3));
    }

    @AfterEach
    void teardown(){
        testDataUtils.cleanUp();
    }

    @Test
    void testAllOffersPage(){

    }
}
