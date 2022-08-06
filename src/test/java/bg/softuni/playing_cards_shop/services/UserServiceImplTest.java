package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.*;
import bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.models.views.AddressDto;
import bg.softuni.playing_cards_shop.models.views.rest.UserPromotedDto;
import bg.softuni.playing_cards_shop.repositories.UserRepository;
import bg.softuni.playing_cards_shop.repositories.UserRoleRepository;
import bg.softuni.playing_cards_shop.services.security.AppUserDetailsService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    @BeforeEach
    void setup(){
        userService=new UserServiceImpl(userRepository,modelMapper,
                passwordEncoder, userRoleService, appUserDetailsService);
        userRoleService=new UserRoleServiceImpl(userRoleRepository);
    }

    @Test
    public void testUsernameExists_UsernameExists(){
        var user= getTestUser("CLIENT",0, 0, 0);

        when(userRepository.existsByUsername(user.getUsername()))
                .thenReturn(true);

        assertTrue(userService.usernameExists(user.getUsername()));
    }

    @Test
    public void testUsernameExists_UsernameDoesNotExist(){
        when(userRepository.existsByUsername(any()))
                .thenReturn(false);

        assertFalse(userService.usernameExists(any()));
    }

    @Test
    public void testEmailExists_EmailExists(){
        var user= getTestUser("CLIENT",0, 0, 0);

        when(userRepository.existsByEmail(user.getEmail()))
                .thenReturn(true);

        assertTrue(userService.emailExists(user.getEmail()));
    }

    @Test
    public void testEmailExists_EmailDoesNotExist(){
        when(userRepository.existsByEmail(any()))
                .thenReturn(false);

        assertFalse(userService.emailExists(any()));
    }

    @Test
    public void testOwnsProfile_Owns(){
        var user= getTestUser("CLIENT",0, 0, 0);

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        assertTrue(userService.ownsProfile(user.getUsername(), user.getId()));
    }

    @Test
    public void testOwnsProfile_DoesNotOwn(){
        var user= getTestUser("CLIENT",0, 0, 0);
        var user1= getTestUser("CLIENT",0, 0,0 ).setUsername("OwnsProfile");

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        assertFalse(userService.ownsProfile(user1.getUsername(), user.getId()));
    }

    @Test
    public void testOwnsProfile_UserDoesNotExist(){
        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(
                ObjectNotFoundException.class,
                () -> userService.ownsProfile("", any())
        );
    }

    @Test
    public void testPromote_UsernameExists(){
        var user=getTestUser("CLIENT",0, 0, 0);

        when(userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        doReturn(Optional.of(new UserRoleEntity().setRole(UserRole.ADMIN)))
                .when(userRoleRepository)
                .findUserRoleEntityByRole(UserRole.ADMIN);

        when(userRepository.save(user))
                .thenAnswer(a->a.getArguments()[0]);

        when(modelMapper.map(user.setRole(new UserRoleEntity().setRole(UserRole.ADMIN)), UserPromotedDto.class))
                .thenReturn(new UserPromotedDto()
                        .setEmail(user.getEmail())
                        .setRole(user.getRole().getRole().name())
                        .setUsername(user.getUsername()));

        var result=userService.promote(user.getUsername());

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(UserRole.ADMIN.name(), result.getRole());
    }

    @Test
    public void testPromote_UsernameDoesNotExist(){
        var user=getTestUser("CLIENT",0, 0, 0);

        when(userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.empty());

        assertThrows(
                ObjectNotFoundException.class,
                ()->userService.promote(user.getUsername())
        );
    }

    @Test
    public void testGetCurrentUser_UserExists(){
        var user=getTestUser("CLIENT",0, 0, 0);
        setupSecurityContext(user);

        when(userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        var result=userService.getCurrentUser();

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getOffers(), result.getOffers());
        assertEquals(user.getRole(), result.getRole());
        assertEquals(user.getAddresses(), result.getAddresses());
        assertEquals(user.getCart(), result.getCart());
        assertEquals(user.getPlacedOrders(), result.getPlacedOrders());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getMyOrders(), result.getMyOrders());
        assertEquals(user.getWishlist(), result.getWishlist());
        assertEquals(user.getReviews(), result.getReviews());
    }

    @Test
    public void testGetCurrentUser_UserDoesNotExist(){
        var user=getTestUser("CLIENT",0, 0, 0);
        setupSecurityContext(user);

        when(userRepository.findUserEntityByUsername(any()))
                .thenReturn(Optional.empty());

        assertThrows(
                ObjectNotFoundException.class,
                ()->userService.getCurrentUser()
        );
    }

    @Test
    public void testFindAddressesByUserUsername_UserExists(){
        var user=getTestUser("CLIENT",2, 0, 0);

        when(userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        for (AddressEntity address : user.getAddresses()) {
            when(modelMapper.map(address, AddressDto.class))
                    .thenReturn(new AddressDto()
                            .setCity(address.getCity())
                            .setCountry(address.getCountry())
                            .setStreet(address.getStreet())
                            .setTelephone(address.getTelephone()));
        }

        var result=userService.findAddressesByUserUsername(user.getUsername());

        assertEquals(user.getAddresses().size(), result.size());
        var userAddressIterator=user.getAddresses().iterator();
        var resultIterator=result.iterator();

        AddressEntity userAddress;
        AddressDto resultAddress;

        while(userAddressIterator.hasNext()){
            userAddress=userAddressIterator.next();
            resultAddress=resultIterator.next();
            assertEquals(userAddress.getCity(), resultAddress.getCity());
            assertEquals(userAddress.getCountry(), resultAddress.getCountry());
            assertEquals(userAddress.getStreet(), resultAddress.getStreet());
            assertEquals(userAddress.getTelephone(), resultAddress.getTelephone());
        }
    }

    @Test
    public void testFindAddressesByUsername_UserDoesNotExist(){
        var user=getTestUser("CLIENT",2, 0, 0);

        when(userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.empty());

        assertThrows(
                ObjectNotFoundException.class,
                ()->userService.findAddressesByUserUsername(user.getUsername())
        );
    }

    @Test
    public void testAddAddress(){
        var user=getTestUser("CLIENT",2, 0, 0);
        final var initialAddresses=new HashSet<>(user.getAddresses());
        var addressToAdd=getTestAddress(user, user.getAddresses().size())
                .setUser(null);

        when(userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        when(userRepository.save(user))
                .thenAnswer(a->a.getArguments()[0]);

        userService.addAddress(user.getUsername(), addressToAdd);

        assertEquals(initialAddresses.size()+1, user.getAddresses().size());

        for (AddressEntity initialAddress : initialAddresses) {
            assertTrue(user.getAddresses().contains(initialAddress));
        }

        assertTrue(user.getAddresses().contains(addressToAdd));
    }

    @Test
    public void testUpdateRatings(){
        var users=getTestUsers(3, 0, new int[]{0,3,7}, 1);

        when(userRepository.findAll())
                .thenReturn(users);

        userService.updateRatings();

        assertEquals(0, users.get(0).getRating());
        assertEquals(3, users.get(1).getRating());
        assertEquals(2, users.get(2).getRating());
    }

    @Test
    public void testHasLiked_HasLiked(){
        var user=getTestUser("CLIENT", 0, 0, 0);
        var deck=getTestDeck();
        var wishlistItem=new WishlistItemEntity()
                .setUser(user)
                .setDeck(deck);
        user.setWishlist(Set.of(wishlistItem));

        when(userRepository.findUserEntityByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        assertTrue(userService.hasLiked(user.getUsername(), deck.getTitle()));
    }

    private UserEntity getTestUser(String role, int addresses, int offers, int reviewsPerOffer){
        var user= new UserEntity()
                .setUsername("Username")
                .setEmail("test@test.com")
                .setRole(new UserRoleEntity().setRole(UserRole.valueOf(role)))
                .setRating((short) 3)
                .setAddresses(null)
                .setWishlist(null)
                .setMyOrders(null)
                .setOffers(null)
                .setPlacedOrders(null)
                .setReviews(null);


        user.setId(1L);
        user.setAddresses(new HashSet<>(getTestAddresses(user, addresses)));
        user.setOffers(new HashSet<>(getTestOffers(user, offers, reviewsPerOffer)));

        return user;
    }

    private DeckEntity getTestDeck(){
        return new DeckEntity()
                .setTitle("Title")
                .setDescription("Description")
                .setPicture(null)
                .setCreator(null)
                .setOffers(null)
                .setCategories(null)
                .setCountryOfOrigin("USA")
                .setDistributor(null)
                .setRecommendedPrice(10);
    }

    private List<UserEntity> getTestUsers(int users, int addresses, int[] offers, int reviews){
        var result=new ArrayList<UserEntity>();
        for (int i = 0; i < users; i++) {
            result.add(getTestUser(i%2==0 ? "CLIENT" : "ADMIN", addresses, offers[i], reviews));
        }
        
        return result;
    }

    private List<AddressEntity> getTestAddresses(UserEntity user,int addresses){
        var result=new ArrayList<AddressEntity>();
        for (int i = 0; i < addresses; i++) {
            result.add(getTestAddress(user, i));
        }

        return result;
    }
    private AddressEntity getTestAddress(UserEntity user, int i){
        return new AddressEntity()
                .setUser(user)
                .setCity("City" + i)
                .setCountry("Country" + i)
                .setStreet("Street" + i)
                .setTelephone("088904509" + i);
    }

    private List<OfferEntity> getTestOffers(UserEntity seller, int offers, int reviewsPerOffer){
        var result=new ArrayList<OfferEntity>();
        for (int i = 0; i < offers; i++) {
            result.add(getTestOffer(seller, reviewsPerOffer, offers));
        }

        return result;
    }

    private OfferEntity getTestOffer(UserEntity seller, int reviews, int offers){
        return new OfferEntity()
                .setDescription("Description")
                .setStatus(OfferStatus.AVAILABLE)
                .setPicture(null)
                .setDeck(null)
                .setQuantity(4)
                .setSeller(seller)
                .setReviews(new HashSet<>(getTestReviews(offers, reviews)));
    }

    private List<ReviewEntity> getTestReviews(int offers, int reviews){
        var result=new ArrayList<ReviewEntity>();
        var sum=0;
        for (int i = 0; i < reviews; i++) {
            sum+=offers;
            result.add(getTestReview(sum%5==0 ? 1 : sum%5));
        }

        return result;
    }

    private ReviewEntity getTestReview(int rating){
        return new ReviewEntity()
                .setReview("Review")
                .setCreated(null)
                .setRating((short) rating)
                .setCreator(null);
    }

    private void setupSecurityContext(UserEntity user){
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(user.getUsername());
    }
}
