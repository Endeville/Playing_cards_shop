package bg.softuni.playing_cards_shop.models.entities;

import bg.softuni.playing_cards_shop.models.entities.enums.UserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private short rating;

    @ManyToOne(optional = false)
    private UserRoleEntity role;

    @ManyToMany
    @JoinTable(name = "users_wishlists")
    private Set<DeckEntity> wishlist;

    @OneToMany(mappedBy = "seller")
    private Set<OfferEntity> offers;

    @OneToMany(mappedBy = "creator")
    private Set<ReviewEntity> reviews;

    @OneToMany
    private Set<NotificationEntity> notifications;

    @OneToMany
    private Set<AddressEntity> addresses;

    @OneToMany
    private Set<CartProductEntity> cart;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(UserRoleEntity role) {
        this.role = role;
        return this;
    }

    public Set<DeckEntity> getWishlist() {
        return wishlist;
    }

    public UserEntity setWishlist(Set<DeckEntity> wishlist) {
        this.wishlist = wishlist;
        return this;
    }

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public UserEntity setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    public short getRating() {
        return rating;
    }

    public UserEntity setRating(short rating) {
        this.rating = rating;
        return this;
    }

    public Set<ReviewEntity> getReviews() {
        return reviews;
    }

    public UserEntity setReviews(Set<ReviewEntity> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Set<NotificationEntity> getNotifications() {
        return notifications;
    }

    public UserEntity setNotifications(Set<NotificationEntity> notifications) {
        this.notifications = notifications;
        return this;
    }

    public Set<AddressEntity> getAddresses() {
        return addresses;
    }

    public UserEntity setAddresses(Set<AddressEntity> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Set<CartProductEntity> getCart() {
        return cart;
    }

    public UserEntity setCart(Set<CartProductEntity> cart) {
        this.cart = cart;
        return this;
    }
}
