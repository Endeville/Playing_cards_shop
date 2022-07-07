package bg.softuni.playing_cards_shop.models.views;

public class DistributorNameDto {
    private String brand;
    private String siteUrl;

    public String getBrand() {
        return brand;
    }

    public DistributorNameDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public DistributorNameDto setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }
}
