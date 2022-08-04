package bg.softuni.playing_cards_shop.models.dtos.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUsernameDto {
    @NotNull(message = "")
    @Size(min = 4, max=20, message = "The username should be between 4 and 20 characters")
    private String username;

    public String getUsername() {
        return username;
    }

    public UserUsernameDto setUsername(String username) {
        this.username = username;
        return this;
    }
}
