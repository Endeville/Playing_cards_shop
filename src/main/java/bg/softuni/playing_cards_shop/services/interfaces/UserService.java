package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;
import bg.softuni.playing_cards_shop.models.entities.AddressEntity;
import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.views.AddressDto;
import bg.softuni.playing_cards_shop.models.views.UserProfileDto;
import bg.softuni.playing_cards_shop.models.views.rest.UserPromotedDto;

import java.util.List;

public interface UserService{

    String OBJECT_NAME_USER="user";

    void register(UserRegistrationDto user);

    boolean emailExists(String email);

    boolean usernameExists(String username);

    UserProfileDto findUserProfileByUsername(String username);

    void addAddress(String name, AddressEntity address);

    UserEntity findUserByUsername(String username);

    List<AddressDto> findAddressesByUserUsername(String username);

    UserEntity getCurrentUser();

    UserPromotedDto promote(String username);

    void updateRatings();

    boolean ownsProfile(String name, Long id);
}
