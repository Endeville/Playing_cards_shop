package bg.softuni.playing_cards_shop.models.entities;

import bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    private DeckEntity deck;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    private UserEntity seller;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    private PictureEntity picture;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ReviewEntity> reviews;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<OrderProductEntity> orderProducts;

    public OfferEntity() {
    }

    public DeckEntity getDeck() {
        return deck;
    }

    public OfferEntity setDeck(DeckEntity deck) {
        this.deck = deck;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OfferEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public OfferEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public Set<ReviewEntity> getReviews() {
        return reviews;
    }

    public OfferEntity setReviews(Set<ReviewEntity> reviews) {
        this.reviews = reviews;
        return this;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public OfferEntity setStatus(OfferStatus status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<OrderProductEntity> getOrderProducts() {
        return orderProducts;
    }

    public OfferEntity setOrderProducts(Set<OrderProductEntity> orderProducts) {
        this.orderProducts = orderProducts;
        return this;
    }
}
