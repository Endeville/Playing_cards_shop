package bg.softuni.playing_cards_shop.models.views;

public class DistributorDetailsDto {
    private String brand;
    private String siteUrl;
    private String description;
    private String picture;

    public String getBrand() {
        return brand;
    }

    public DistributorDetailsDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public DistributorDetailsDto setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DistributorDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public DistributorDetailsDto setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
