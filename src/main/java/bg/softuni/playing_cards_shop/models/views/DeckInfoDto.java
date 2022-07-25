package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class DeckInfoDto {
    private String title;
    private String description;
    private List<String> pictures;
    private String countryOfOrigin;
    private List<CategoryDto> categories;
    private String creatorName;
    private String distributorBrand;

    public String getTitle() {
        return title;
    }

    public DeckInfoDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeckInfoDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public DeckInfoDto setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public DeckInfoDto setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public DeckInfoDto setCategories(List<CategoryDto> categories) {
        this.categories = categories;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public DeckInfoDto setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getDistributorBrand() {
        return distributorBrand;
    }

    public DeckInfoDto setDistributorBrand(String distributorBrand) {
        this.distributorBrand = distributorBrand;
        return this;
    }
}
