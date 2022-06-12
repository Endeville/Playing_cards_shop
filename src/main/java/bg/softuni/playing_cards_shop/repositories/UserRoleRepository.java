package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.UserRoleEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    public UserRoleEntity findUserRoleEntityByRole(UserRole role);
}
