package bg.softuni.playing_cards_shop.models.views;

import java.math.BigDecimal;

public class OrderProductDetailsDto {
    private OfferViewDto offer;
    private int quantity;

    public OfferViewDto getOffer() {
        return offer;
    }

    public OrderProductDetailsDto setOffer(OfferViewDto offer) {
        this.offer = offer;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderProductDetailsDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
