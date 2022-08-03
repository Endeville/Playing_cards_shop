package bg.softuni.playing_cards_shop.models.dtos.rest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class CartProductUpdateDto {
    @NotNull
    private Long offerId;

    @Min(0)
    private int quantity;

    @Pattern(regexp = "minus|plus")
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
