package bg.softuni.playing_cards_shop.models.entities;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

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
}
