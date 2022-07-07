package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;

public interface UserService{

    void register(UserRegistrationDto user);

    boolean emailExists(String email);

    boolean usernameExists(String username);
}
