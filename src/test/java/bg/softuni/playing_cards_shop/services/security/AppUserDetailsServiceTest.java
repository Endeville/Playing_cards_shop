package bg.softuni.playing_cards_shop.services.security;

import bg.softuni.playing_cards_shop.models.entities.*;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.repositories.UserRepository;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private AppUserDetailsService appUserDetailsServiceToTest;

    @BeforeEach
    void setUp(){
        this.appUserDetailsServiceToTest=new AppUserDetailsService(userRepository);
    }


    @Test
    void testLoadUserByUsername_UserExists(){
        var user=new UserEntity()
                .setUsername("Test")
                .setEmail("test@test.com")
                .setPassword("123456")
                .setRating((short) 5)
                .setRole(new UserRoleEntity().setRole(UserRole.CLIENT))
                .setWishlist(new HashSet<>())
                .setOffers(new HashSet<>())
                .setReviews(new HashSet<>())
                .setAddresses(new HashSet<>())
                .setCart(new HashSet<>())
                .setPlacedOrders(new HashSet<>())
                .setMyOrders(new HashSet<>());

        when(this.userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        var appUser= (AppUser) appUserDetailsServiceToTest.loadUserByUsername(user.getUsername());


        Assertions.assertEquals(user.getUsername(), appUser.getUsername());
        Assertions.assertEquals(user.getPassword(), user.getPassword());

        Assertions.assertEquals(1, appUser.getAuthorities().size());
        Assertions.assertEquals("ROLE_" + UserRole.CLIENT.name(), appUser.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist(){
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> appUserDetailsServiceToTest.loadUserByUsername("NonExistentUser")
        );
    }
}
