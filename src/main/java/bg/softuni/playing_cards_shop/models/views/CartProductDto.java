package bg.softuni.playing_cards_shop.models.views;

import java.math.BigDecimal;

public class CartProductDto {
    private OfferViewDto offer;
    private int quantity;


    public OfferViewDto getOffer() {
        return offer;
    }

    public CartProductDto setOffer(OfferViewDto offer) {
        this.offer = offer;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartProductDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
