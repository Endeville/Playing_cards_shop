package bg.softuni.playing_cards_shop.models.views;

import java.math.BigDecimal;
import java.util.List;

public class OfferDetailsDto {
    private String deckTitle;
    private String sellerUsername;
    private BigDecimal price;
    private int quantity;
    private String description;
    private String status;
    private List<String> pictures;

    public String getDeckTitle() {
        return deckTitle;
    }

    public OfferDetailsDto setDeckTitle(String deckTitle) {
        this.deckTitle = deckTitle;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public OfferDetailsDto setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OfferDetailsDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OfferDetailsDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public OfferDetailsDto setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }
}
