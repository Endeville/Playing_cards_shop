package bg.softuni.playing_cards_shop.models.entities;

import bg.softuni.playing_cards_shop.models.dtos.AddAddressDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class AddressEntity  extends BaseEntity{

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    private String telephone;

    @ManyToOne(cascade = CascadeType.DETACH)
    private UserEntity user;

    @OneToMany(mappedBy = "address", cascade = CascadeType.DETACH)
    private Set<OrderEntity> orders;

    public String getCountry() {
        return country;
    }

    public AddressEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public AddressEntity setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public AddressEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public AddressEntity setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
