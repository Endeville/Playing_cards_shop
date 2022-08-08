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

import java.nio.channels.MulticastChannel;

import static bg.softuni.playing_cards_shop.utils.TestDataUtils.emptyFile;
import static bg.softuni.playing_cards_shop.utils.TestDataUtils.testCreator;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CreatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setup() {
        if(!TestDataUtils.isSetUp) {
            testDataUtils.init();
        }
    }

    @Test
    @WithMockUser(username = "TestAdmin1", roles = "ADMIN")
    void testAddCreatorPage() throws Exception {
        mockMvc.perform(get("/creators/add")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("addCreator"));
    }

    @Test
    @WithMockUser(username = "TestAdmin1", roles = "ADMIN")
    void testAddCreator_Allowed() throws Exception {
        mockMvc.perform(multipart("/creators/add")
                        .file("picture", emptyFile.getBytes())
                        .param("name", "TestName")
                        .param("description", "TestDescription")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/decks/all"));
    }

    @Test
    @WithMockUser(username = "TestAdmin1", roles = "ADMIN")
    void testAddCreator_Error() throws Exception {
        mockMvc.perform(multipart("/creators/add")
                        .file("picture", emptyFile.getBytes())
                        .param("name", "testCreator")
                        .param("description", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/creators/add"));

        mockMvc.perform(multipart("/creators/add")
                        .file("picture", emptyFile.getBytes())
                        .param("name", "")
                        .param("description", "Description")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/creators/add"));
    }

    @Test
    @WithMockUser(username = "TestClient1", roles = "CLIENT")
    void testAddCreator_Unauthorized() throws Exception {
        mockMvc.perform(get("/creators/add")
                        .with(csrf()))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(multipart("/creators/add")
                        .file("picture", emptyFile.getBytes())
                        .param("name", "testCreator")
                        .param("description", "testDescription")
                        .with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "TestClient1", roles="CLIENT")
    void testCreatorDetails() throws Exception {
        mockMvc.perform(get("/creators/{name}", testCreator.getName())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("creatorDetails"))
                .andExpect(model().attributeExists("creator"));
    }


}
