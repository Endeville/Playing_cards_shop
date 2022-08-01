package bg.softuni.playing_cards_shop.models.dtos.rest;

public class CartProductUpdateDto {
    private Long offerId;
    private int quantity;
    private String operation;

    public Long getOfferId() {
        return offerId;
    }

    public CartProductUpdateDto setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartProductUpdateDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public CartProductUpdateDto setOperation(String operation) {
        this.operation = operation;
        return this;
    }
}
