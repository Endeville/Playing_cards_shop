package bg.softuni.playing_cards_shop.models.views;

import bg.softuni.playing_cards_shop.models.entities.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderDetailsDto {
    private Instant orderTime;
    private BigDecimal price;
    private String notes;
    private String status;
    private AddressDto address;
    private String customerUsername;
    private List<OrderProductDetailsDto> products;

    public Instant getOrderTime() {
        return orderTime;
    }

    public OrderDetailsDto setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderDetailsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public OrderDetailsDto setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderDetailsDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public AddressDto getAddress() {
        return address;
    }

    public OrderDetailsDto setAddress(AddressDto address) {
        this.address = address;
        return this;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public OrderDetailsDto setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
        return this;
    }

    public List<OrderProductDetailsDto> getProducts() {
        return products;
    }

    public OrderDetailsDto setProducts(List<OrderProductDetailsDto> products) {
        this.products = products;
        return this;
    }
}
