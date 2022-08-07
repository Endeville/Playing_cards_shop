package bg.softuni.playing_cards_shop.web;


import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.views.AddressDto;
import bg.softuni.playing_cards_shop.utils.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testClient;

    @BeforeEach
    void setup(){
        testClient=testDataUtils.createTestClient("testClient", "email@test.com");
        testDataUtils.initRoles();
    }

    @AfterEach
    void teardown(){
        testDataUtils.cleanUp();
    }

    @Test
    @WithMockUser(username = "testClient", roles = "CLIENT")
    void testAddAddressPage() throws Exception {
        mockMvc.perform(get("/addresses/add")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("addAddress"));
    }

    @Test
    @WithMockUser(username = "testClient", roles = "CLIENT")
    void testAddAddress() throws Exception {
        mockMvc.perform(post("/addresses/add")
                .param("country", "CountryTest")
                .param("city", "CityTest")
                .param("street", "StreetTest")
                .param("telephone", "0881234567")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/profile/" + testClient.getUsername()));

        mockMvc.perform(post("/addresses/add")
                        .param("country", "c")
                        .param("city", "c")
                        .param("street", "s")
                        .param("telephone", "12345678")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/addresses/add"));
    }
}

