package bg.softuni.playing_cards_shop.models.views;

public class OrderProductInfoDto {
    private String offerDeckTitle;
    private int quantity;

    public String getOfferDeckTitle() {
        return offerDeckTitle;
    }

    public OrderProductInfoDto setOfferDeckTitle(String offerDeckTitle) {
        this.offerDeckTitle = offerDeckTitle;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderProductInfoDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return this.offerDeckTitle + " - " + this.quantity;
    }
}
