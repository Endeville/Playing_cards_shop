package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddressAddDto {
    @NotBlank
    @Size(min=2, max=40)
    private String country;

    @NotBlank
    @Size(min=2, max=100)
    private String city;

    @NotBlank
    @Size(min=2, max=200)
    private String street;

    @NotBlank
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private String telephone;

    public String getCountry() {
        return country;
    }

    public AddressAddDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressAddDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressAddDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public AddressAddDto setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
}
