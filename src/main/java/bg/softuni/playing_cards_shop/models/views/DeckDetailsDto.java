package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class DeckDetailsDto {
    private String title;
    private String description;
    private List<String> pictures;
    private String countryOfOrigin;
    private List<CategoryDto> categories;
    private String creatorName;
    private String distributorBrand;
    private boolean liked;

    public String getTitle() {
        return title;
    }

    public DeckDetailsDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeckDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public DeckDetailsDto setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public DeckDetailsDto setCategories(List<CategoryDto> categories) {
        this.categories = categories;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public DeckDetailsDto setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getDistributorBrand() {
        return distributorBrand;
    }

    public DeckDetailsDto setDistributorBrand(String distributorBrand) {
        this.distributorBrand = distributorBrand;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public DeckDetailsDto setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }

    public boolean isLiked() {
        return liked;
    }

    public DeckDetailsDto setLiked(boolean liked) {
        this.liked = liked;
        return this;
    }
}
