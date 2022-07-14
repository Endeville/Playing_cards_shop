package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class CatalogOfferDto {
    private Long id;
    private String title;
    private String description;
    private List<String> pictures;

    public Long getId() {
        return id;
    }

    public CatalogOfferDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CatalogOfferDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CatalogOfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public CatalogOfferDto setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }
}
