package bg.softuni.playing_cards_shop.models.views;

public class OrderProductDto {
    private String offerDeckTitle;
    private int quantity;

    public String getOfferDeckTitle() {
        return offerDeckTitle;
    }

    public OrderProductDto setOfferDeckTitle(String offerDeckTitle) {
        this.offerDeckTitle = offerDeckTitle;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderProductDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return this.offerDeckTitle + " - " + this.quantity;
    }
}
