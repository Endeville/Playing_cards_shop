package bg.softuni.playing_cards_shop.models.views;

import java.math.BigDecimal;

public class CartProductDto {
    private Long offerId;
    private String offerDeckTitle;
    private int quantity;
    private String offerSellerUsername;
    private BigDecimal offerPrice;
    private int offerQuantity;

    public Long getOfferId() {
        return offerId;
    }

    public CartProductDto setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getOfferDeckTitle() {
        return offerDeckTitle;
    }

    public CartProductDto setOfferDeckTitle(String offerDeckTitle) {
        this.offerDeckTitle = offerDeckTitle;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartProductDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getOfferSellerUsername() {
        return offerSellerUsername;
    }

    public CartProductDto setOfferSellerUsername(String offerSellerUsername) {
        this.offerSellerUsername = offerSellerUsername;
        return this;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public CartProductDto setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public int getOfferQuantity() {
        return offerQuantity;
    }

    public CartProductDto setOfferQuantity(int offerQuantity) {
        this.offerQuantity = offerQuantity;
        return this;
    }
}
