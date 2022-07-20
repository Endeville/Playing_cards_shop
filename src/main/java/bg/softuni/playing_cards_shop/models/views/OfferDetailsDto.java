package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class OfferDetailsDto {
    private Long id;
    private String deckTitle;
    private String sellerUsername;
    private Double price;
    private int quantity;
    private String description;
    private String status;
    private List<String> pictures;

    public Long getId() {
        return id;
    }

    public OfferDetailsDto setId(Long id) {
        this.id = id;
        return this;
    }

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

    public Double getPrice() {
        return price;
    }

    public OfferDetailsDto setPrice(Double price) {
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
