package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class DeckDetailsDto {
    private String title;
    private String description;
    private List<PictureDto> pictures;
    private String country;
    private List<CategoryDto> categories;
    private CreatorIdDto creator;
    private DistributorIdentificationDto distributor;

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

    public List<PictureDto> getPictures() {
        return pictures;
    }

    public DeckDetailsDto setPictures(List<PictureDto> pictures) {
        this.pictures = pictures;
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

    public CreatorIdDto getCreator() {
        return creator;
    }

    public DeckDetailsDto setCreator(CreatorIdDto creator) {
        this.creator = creator;
        return this;
    }

    public DistributorIdentificationDto getDistributor() {
        return distributor;
    }

    public DeckDetailsDto setDistributor(DistributorIdentificationDto distributor) {
        this.distributor = distributor;
        return this;
    }
}
