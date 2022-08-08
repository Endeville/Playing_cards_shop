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

    @BeforeEach
    void setup(){

    }

    @Test
    void testAllOffersPage(){

    }
}
