package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="wishlist_items")
public class WishlistItemEntity extends BaseEntity{

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private DeckEntity deck;

    public UserEntity getUser() {
        return user;
    }

    public WishlistItemEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public DeckEntity getDeck() {
        return deck;
    }

    public WishlistItemEntity setDeck(DeckEntity deck) {
        this.deck = deck;
        return this;
    }
}
