package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @ManyToOne
    private DeckEntity deck;

    @ManyToOne
    private UserEntity seller;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @OneToMany
    @JoinColumn(name = "offer_id")
    private Set<PictureEntity> additionalPictures;

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

    public Set<PictureEntity> getAdditionalPictures() {
        return additionalPictures;
    }

    public OfferEntity setAdditionalPictures(Set<PictureEntity> additionalPictures) {
        this.additionalPictures = additionalPictures;
        return this;
    }
}
