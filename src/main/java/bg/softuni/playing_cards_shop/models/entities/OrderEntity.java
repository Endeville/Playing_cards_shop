package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    private Instant orderTime;

    private BigDecimal price;

    private String notes;

    @ManyToOne
    private AddressEntity address;

    @ManyToOne
    private UserEntity customer;

    @OneToMany
    private Set<OrderProductEntity> products;


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
}
