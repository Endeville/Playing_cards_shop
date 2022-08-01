package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class OfferInfoDto {
    private String deckTitle;
    private String sellerUsername;
    private Double price;
    private int quantity;
    private String description;
    private String status;
    private String picture;

    public String getDeckTitle() {
        return deckTitle;
    }

    public OfferInfoDto setDeckTitle(String deckTitle) {
        this.deckTitle = deckTitle;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public OfferInfoDto setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OfferInfoDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OfferInfoDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferInfoDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OfferInfoDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public OfferInfoDto setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
