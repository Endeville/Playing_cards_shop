package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.utils.TestDataUtils;
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
public class RequestsControllerTests {

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
    @WithMockUser(username = "TestClient1", roles = "CLIENT")
    public void testAddRequestPage() throws Exception {
        mockMvc.perform(get("/requests/add")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("addRequest"));
    }

    @Test
    public void testAddRequestPage_Unauthorized() throws Exception {
        mockMvc.perform(get("/requests/add")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));
    }

    @Test
    @WithMockUser(username = "TestClient1", roles = "CLIENT")
    public void testRequestAdd_Valid() throws Exception {
        mockMvc.perform(post("/requests/add")
                .param("content", "TestRequest")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/decks/all"));
    }

    @Test
    @WithMockUser(username = "TestClient1", roles = "CLIENT")
    public void testRequestAdd_Error() throws Exception {
        mockMvc.perform(post("/requests/add")
                        .param("content", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/requests/add"));
    }

    @Test
    @WithMockUser(username = "TestAdmin1", roles = "ADMIN")
    public void testRequestsPage() throws Exception {
        mockMvc.perform(get("/requests/all")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("requests"))
                .andExpect(model().attributeExists("requests"));
    }

    @Test
    @WithMockUser(username = "TestClient1", roles = "CLIENT")
    public void testRequestsPage_Unauthorized() throws Exception {
        mockMvc.perform(get("/requests/all")
                .with(csrf()))
                .andExpect(status().is4xxClientError());
    }
}
