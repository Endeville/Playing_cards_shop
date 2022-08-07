package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.entities.UserRoleEntity;
import bg.softuni.playing_cards_shop.repositories.UserRepository;
import bg.softuni.playing_cards_shop.services.interfaces.UserRoleService;
import bg.softuni.playing_cards_shop.services.security.AppUserDetailsService;
import bg.softuni.playing_cards_shop.utils.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testClient;

    @BeforeEach
    void setup(){
        testDataUtils.initRoles();
        testClient=testDataUtils.createTestClient("testClient", "email1@test.com");
        testDataUtils.createTestAdmin("testAdmin", "email2@test.com");
    }

    @AfterEach
    void teardown(){
        testDataUtils.cleanUp();
    }

    @Test
    void testAuthorizationPage() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("account"));

        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("account"));
    }

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("username", "TestUsername")
                .param("email", "test@email.com")
                .param("password", "123456")
                .param("rePass", "123456")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/decks/all"));

        //unique email validation
        mockMvc.perform(post("/users/register")
                .param("username", "TestUsername1")
                .param("email", "test@email.com")
                .param("password", "123456")
                .param("rePass", "123456")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

        //unique username validation
        mockMvc.perform(post("/users/register")
                        .param("username", "TestUsername")
                        .param("email", "test1@email.com")
                        .param("password", "123456")
                        .param("rePass", "123456")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }

    @Test
    void testLoginError() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("username", "invalidUsername")
                .param("password", "123456")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(forwardedUrl("/users/login-error"));
    }

    @Test
    @WithMockUser(username = "testClient", roles = "CLIENT")
    void testProfilePage() throws Exception {
        mockMvc.perform(get("/users/profile/{username}",testClient.getUsername())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("userProfile"))
                .andExpect(model().attributeExists("user"));
    }
}
