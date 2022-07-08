package bg.softuni.playing_cards_shop.models.views;

public class AddressDto {
    private String country;
    private String city;
    private String street;
    private String telephone;

    public String getCountry() {
        return country;
    }

    public AddressDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public AddressDto setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(street).append(", ")
                .append(city).append(", ")
                .append(country).append(" - ")
                .append(telephone)
                .toString();
    }
}
