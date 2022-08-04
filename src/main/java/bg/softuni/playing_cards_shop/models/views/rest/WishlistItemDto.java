package bg.softuni.playing_cards_shop.models.views.rest;

public class WishlistItemDto {
    private String userUsername;
    private String deckTitle;

    public String getUserUsername() {
        return userUsername;
    }

    public WishlistItemDto setUserUsername(String userUsername) {
        this.userUsername = userUsername;
        return this;
    }

    public String getDeckTitle() {
        return deckTitle;
    }

    public WishlistItemDto setDeckTitle(String deckTitle) {
        this.deckTitle = deckTitle;
        return this;
    }
}
