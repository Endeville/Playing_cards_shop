package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.UserRoleEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import bg.softuni.playing_cards_shop.repositories.UserRoleRepository;
import bg.softuni.playing_cards_shop.services.interfaces.UserRoleService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public UserRoleEntity findByRole(UserRole role) {
        return userRoleRepository.findUserRoleEntityByRole(role)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_USER_ROLE));
    }
}
