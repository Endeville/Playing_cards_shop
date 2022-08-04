package bg.softuni.playing_cards_shop.models.views.rest;

public class CartProductEssentialsDto {
    private String offerDeckTitle;
    private String offerSellerUsername;
    private String userUsername;


    public String getOfferDeckTitle() {
        return offerDeckTitle;
    }

    public CartProductEssentialsDto setOfferDeckTitle(String offerDeckTitle) {
        this.offerDeckTitle = offerDeckTitle;
        return this;
    }

    public String getOfferSellerUsername() {
        return offerSellerUsername;
    }

    public CartProductEssentialsDto setOfferSellerUsername(String offerSellerUsername) {
        this.offerSellerUsername = offerSellerUsername;
        return this;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public CartProductEssentialsDto setUserUsername(String userUsername) {
        this.userUsername = userUsername;
        return this;
    }
}
