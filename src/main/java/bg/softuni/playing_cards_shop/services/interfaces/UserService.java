package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.UserLoginDto;
import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;

public interface UserService{

    void register(UserRegistrationDto user);

    void login(UserEntity user);

    boolean emailExists(String email);

    boolean login(UserLoginDto userLoginDto);

    void logout();

    boolean usernameExists(String username);
}
