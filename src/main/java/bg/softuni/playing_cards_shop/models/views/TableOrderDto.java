package bg.softuni.playing_cards_shop.models.views;

import bg.softuni.playing_cards_shop.models.entities.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class TableOrderDto {
    private Long id;
    private String sellerUsername;
    private Instant orderTime;
    private BigDecimal price;
    private OrderStatus status;
    private List<OrderProductInfoDto> products;

    public Long getId() {
        return id;
    }

    public TableOrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public TableOrderDto setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public TableOrderDto setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TableOrderDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public TableOrderDto setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public List<OrderProductInfoDto> getProducts() {
        return products;
    }

    public TableOrderDto setProducts(List<OrderProductInfoDto> products) {
        this.products = products;
        return this;
    }
}
