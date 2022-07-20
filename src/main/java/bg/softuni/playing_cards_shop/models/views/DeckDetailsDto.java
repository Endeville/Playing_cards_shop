package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class DeckDetailsDto {
    private String title;
    private String description;
    private List<String> pictures;
    private String country;
    private List<CategoryDto> categories;
    private CreatorNameDto creator;
    private DistributorBrandDto distributor;
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

    public String getCountry() {
        return country;
    }

    public DeckDetailsDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public DeckDetailsDto setCategories(List<CategoryDto> categories) {
        this.categories = categories;
        return this;
    }

    public CreatorNameDto getCreator() {
        return creator;
    }

    public DeckDetailsDto setCreator(CreatorNameDto creator) {
        this.creator = creator;
        return this;
    }

    public DistributorBrandDto getDistributor() {
        return distributor;
    }

    public DeckDetailsDto setDistributor(DistributorBrandDto distributor) {
        this.distributor = distributor;
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
