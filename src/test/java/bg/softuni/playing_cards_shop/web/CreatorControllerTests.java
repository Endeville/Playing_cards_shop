package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.repositories.CreatorRepository;
import bg.softuni.playing_cards_shop.repositories.UserRoleRepository;
import bg.softuni.playing_cards_shop.utils.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CreatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testClient, testAdmin;
    private CreatorEntity testCreator;
    private PictureEntity testPicture;

    @BeforeEach
    void setup() {
        testClient = testDataUtils.createTestClient("testClient", "test1@email.com");
        testAdmin = testDataUtils.createTestAdmin("testAdmin", "test2@email.com");
//        testPicture=testDataUtils.getTestPicture();
//        testCreator=testDataUtils.createTestCreator("testCreator", null, testPicture );
//        testDataUtils.initRoles();
    }

    @AfterEach
    void teardown() {
        testDataUtils.cleanUp();
    }

    @Test
    @WithMockUser(username = "testAdmin", roles = "ADMIN")
    void testAddCreatorPage() throws Exception {
        mockMvc.perform(get("/creators/add")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("addCreator"));
    }

//    @Test
//    @WithMockUser(username = "testAdmin", roles = "ADMIN")
//    void testAddCreator_Allowed() throws Exception {
//        mockMvc.perform(post("/creators/add")
//                        .param("name", "TestName")
//                        .param("description", "TestDescription")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/decks/all"));
//    }
//
//    @Test
//    @WithMockUser(username = "testAdmin", roles = "ADMIN")
//    void testAddCreator_Error() throws Exception {
//        mockMvc.perform(post("/creators/add")
//                        .param("name", "testCreator")
//                        .param("description", "TestDescription")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/creators/add"));
//    }
}
