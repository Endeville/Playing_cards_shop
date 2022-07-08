package bg.softuni.playing_cards_shop.models.views;

public class DistributorIdentificationDto {
    private Long id;
    private String brand;

    public Long getId() {
        return id;
    }

    public DistributorIdentificationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public DistributorIdentificationDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }
}
