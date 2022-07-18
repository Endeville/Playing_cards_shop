package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartProductEntity extends BaseEntity{
    @Column(nullable = false)
    private int quantity;

    @ManyToOne(optional = false)
    private OfferEntity offer;

    public int getQuantity() {
        return quantity;
    }

    public CartProductEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OfferEntity getOffer() {
        return offer;
    }

    public CartProductEntity setOffer(OfferEntity offer) {
        this.offer = offer;
        return this;
    }
}
