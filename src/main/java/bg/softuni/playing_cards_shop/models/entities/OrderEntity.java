package bg.softuni.playing_cards_shop.models.entities;

import bg.softuni.playing_cards_shop.models.entities.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @Column(nullable = false)
    private Instant orderTime;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String notes;

//    todo: add rated functionality - ask for the customer to rate the service
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean rated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.DETACH)
    private AddressEntity address;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private UserEntity customer;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "order")
    private Set<OrderProductEntity> products;

    @ManyToOne
    private UserEntity seller;


    public Instant getOrderTime() {
        return orderTime;
    }

    public OrderEntity setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public OrderEntity setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }

    public Set<OrderProductEntity> getProducts() {
        return products;
    }

    public OrderEntity setProducts(Set<OrderProductEntity> products) {
        this.products = products;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public OrderEntity setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public OrderEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderEntity setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OrderEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public boolean isRated() {
        return rated;
    }

    public OrderEntity setRated(boolean rated) {
        this.rated = rated;
        return this;
    }
}
