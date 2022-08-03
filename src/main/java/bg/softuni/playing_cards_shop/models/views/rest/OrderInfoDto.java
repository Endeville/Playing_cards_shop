package bg.softuni.playing_cards_shop.models.views.rest;

import java.math.BigDecimal;

public class OrderInfoDto {
    private Long id;
    private String status;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public OrderInfoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderInfoDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderInfoDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
