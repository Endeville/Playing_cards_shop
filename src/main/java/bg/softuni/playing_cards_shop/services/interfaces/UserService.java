package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;
import bg.softuni.playing_cards_shop.models.entities.AddressEntity;
import bg.softuni.playing_cards_shop.models.views.UserProfileDto;

public interface UserService{

    void register(UserRegistrationDto user);

    boolean emailExists(String email);

    boolean usernameExists(String username);

    UserProfileDto findUserProfileByUsername(String username);

    void addAddress(String name, AddressEntity address);
}
