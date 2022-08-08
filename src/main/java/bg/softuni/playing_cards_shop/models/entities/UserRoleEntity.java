package bg.softuni.playing_cards_shop.models.entities;

import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRoleEntity() {
    }

    public UserRole getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
