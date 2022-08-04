package bg.softuni.playing_cards_shop.models.views.rest;

public class UserPromotedDto {
    private String username;
    private String email;
    private String role;

    public String getUsername() {
        return username;
    }

    public UserPromotedDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserPromotedDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserPromotedDto setRole(String role) {
        this.role = role;
        return this;
    }
}
