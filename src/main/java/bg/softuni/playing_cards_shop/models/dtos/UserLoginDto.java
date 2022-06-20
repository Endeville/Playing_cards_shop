package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDto {

    @NotNull(message = "")
    @Size(min = 4, max=20, message = "Please fill in the field")
    private String username;

    @NotNull(message = "")
    @Size(min=6, max=30, message = "Please fill in the field")
    private String password;

    public UserLoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
