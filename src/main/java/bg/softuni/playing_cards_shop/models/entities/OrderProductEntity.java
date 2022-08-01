package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_products")
public class OrderProductEntity extends BaseEntity{

    @ManyToOne
    private OfferEntity offer;

    private int quantity;

    public OfferEntity getOffer() {
        return offer;
    }

    public OrderProductEntity setOffer(OfferEntity offer) {
        this.offer = offer;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderProductEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
