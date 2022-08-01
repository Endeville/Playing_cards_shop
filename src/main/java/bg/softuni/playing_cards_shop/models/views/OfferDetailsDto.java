package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class OfferDetailsDto {
    private Long id;
    private DeckIdentificationDto deck;
    private String sellerUsername;
    private Double price;
    private int quantity;
    private String description;
    private String status;
    private List<String> pictures;
    private boolean carted;

    public Long getId() {
        return id;
    }

    public OfferDetailsDto setId(Long id) {
        this.id = id;
        return this;
    }

    public DeckIdentificationDto getDeck() {
        return deck;
    }

    public OfferDetailsDto setDeck(DeckIdentificationDto deck) {
        this.deck = deck;
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

    public boolean isCarted() {
        return carted;
    }

    public OfferDetailsDto setCarted(boolean carted) {
        this.carted = carted;
        return this;
    }
}
