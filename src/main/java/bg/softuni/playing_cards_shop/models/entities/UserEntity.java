package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.util.Objects;
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

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    private UserRoleEntity role;

    @OneToMany(mappedBy = "user")
    private Set<WishlistItemEntity> wishlist;

    @OneToMany(mappedBy = "seller")
    private Set<OfferEntity> offers;

    @OneToMany(mappedBy = "creator")
    private Set<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH)
    private Set<AddressEntity> addresses;

    @OneToMany(mappedBy = "customer")
    private Set<CartProductEntity> cart;

    @OneToMany(mappedBy = "customer")
    private Set<OrderEntity> placedOrders;

    @OneToMany(mappedBy = "seller")
    private Set<OrderEntity> myOrders;

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

    public Set<WishlistItemEntity> getWishlist() {
        return wishlist;
    }

    public UserEntity setWishlist(Set<WishlistItemEntity> wishlist) {
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

    public Set<OrderEntity> getPlacedOrders() {
        return placedOrders;
    }

    public UserEntity setPlacedOrders(Set<OrderEntity> placedOrders) {
        this.placedOrders = placedOrders;
        return this;
    }

    public Set<OrderEntity> getMyOrders() {
        return myOrders;
    }

    public UserEntity setMyOrders(Set<OrderEntity> myOrders) {
        this.myOrders = myOrders;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return rating == that.rating && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(wishlist, that.wishlist) && Objects.equals(offers, that.offers) && Objects.equals(reviews, that.reviews) && Objects.equals(addresses, that.addresses) && Objects.equals(cart, that.cart) && Objects.equals(placedOrders, that.placedOrders) && Objects.equals(myOrders, that.myOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, rating, role, wishlist, offers, reviews, addresses, cart, placedOrders, myOrders);
    }
}
