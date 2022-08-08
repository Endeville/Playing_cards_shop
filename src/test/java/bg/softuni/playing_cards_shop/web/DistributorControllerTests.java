package bg.softuni.playing_cards_shop.web;


import bg.softuni.playing_cards_shop.utils.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static bg.softuni.playing_cards_shop.utils.TestDataUtils.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class DistributorControllerTests {

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
    void testAddDistributorPage() throws Exception {
        mockMvc.perform(get("/distributors/add")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("addDistributor"));
    }

    @Test
    @WithMockUser(username = "TestAdmin1", roles = "ADMIN")
    void testAddCreator_Allowed() throws Exception {
        mockMvc.perform(multipart("/distributors/add")
                        .file("picture", emptyFile.getBytes())
                        .param("brand", "TestBrand")
                        .param("siteUrl", "https://site.com")
                        .param("description", "TestDescription")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/decks/all"));
    }

    @Test
    @WithMockUser(username = "TestAdmin1", roles = "ADMIN")
    void testAddCreator_Error() throws Exception {

        mockMvc.perform(multipart("/distributors/add")
                        .file("picture", emptyFile.getBytes())
                        .param("brand", "")
                        .param("siteUrl", "https://site.com")
                        .param("description", "TestDescription")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/distributors/add"));

        mockMvc.perform(multipart("/distributors/add")
                        .file("picture", emptyFile.getBytes())
                        .param("brand", "TestBrand")
                        .param("siteUrl", "https://sit")
                        .param("description", "TestDescription")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/distributors/add"));

        mockMvc.perform(multipart("/distributors/add")
                        .file("picture", emptyFile.getBytes())
                        .param("brand", "TestBrand")
                        .param("siteUrl", "")
                        .param("description", "TestDescription")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/distributors/add"));

        mockMvc.perform(multipart("/distributors/add")
                        .file("picture", emptyFile.getBytes())
                        .param("brand", "TestBrand")
                        .param("siteUrl", "https://site.com")
                        .param("description", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/distributors/add"));
    }

    @Test
    @WithMockUser(username = "TestClient1", roles = "CLIENT")
    void testAddCreator_Unauthorized() throws Exception {
        mockMvc.perform(multipart("/distributors/add")
                        .file("picture", emptyFile.getBytes())
                        .param("brand", "TestBrand")
                        .param("siteUrl", "https://site.com")
                        .param("description", "TestDescription")
                        .with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "TestClient1", roles="CLIENT")
    void testCreatorDetails() throws Exception {
        mockMvc.perform(get("/distributors/{brans}", testDistributor.getBrand())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("distributorDetails"))
                .andExpect(model().attributeExists("distributor"));
    }
}
