package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.UserRoleEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;

public interface UserRoleService {

    String OBJECT_NAME_USER_ROLE="user role";

    UserRoleEntity findByRole(UserRole client);
}
