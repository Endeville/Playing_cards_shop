package bg.softuni.playing_cards_shop.models.views.rest;

import java.math.BigDecimal;

public class CartProductPriceQuantityDto {
    private int quantity;
    private BigDecimal price;
    private int maxQuantity;
    private BigDecimal pricePerProduct;

    public int getQuantity() {
        return quantity;
    }

    public CartProductPriceQuantityDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CartProductPriceQuantityDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public CartProductPriceQuantityDto setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
        return this;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public CartProductPriceQuantityDto setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
        return this;
    }
}
