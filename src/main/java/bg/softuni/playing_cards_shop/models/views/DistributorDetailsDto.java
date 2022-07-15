package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class DistributorDetailsDto {
    private String brand;
    private String siteUrl;
    private String description;
    private List<String> pictures;

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

    public List<String> getPictures() {
        return pictures;
    }

    public DistributorDetailsDto setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }
}
