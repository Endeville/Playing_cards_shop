package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddAddressDto {
    @NotBlank(message = "")
    @Size(min=2, max=40, message = "Please fill in the country(up to 40 characters)")
    private String country;

    @NotBlank(message = "")
    @Size(min=2, max=50, message = "Please fill in the city(up to 50 characters).")
    private String city;

    @NotBlank(message = "")
    @Size(min=2, max=100, message = "Please fill in the street(up to 100 characters).")
    private String street;

    @NotBlank(message = "")
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "Please enter a valid telephone number.")
    private String telephone;

    public String getCountry() {
        return country;
    }

    public AddAddressDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddAddressDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddAddressDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public AddAddressDto setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
}
