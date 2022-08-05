package bg.softuni.playing_cards_shop.models.views;
import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;

import java.util.List;

public class UserProfileDto {
    private Long id;
    private String username;
    private String email;
    private short rating;
    private List<AddressDto> addresses;
    private UserRole role;

    public Long getId() {
        return id;
    }

    public UserProfileDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public short getRating() {
        return rating;
    }

    public UserProfileDto setRating(short rating) {
        this.rating = rating;
        return this;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public UserProfileDto setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
        return this;
    }

    public String getRole() {
        return role.name();
    }

    public UserProfileDto setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
