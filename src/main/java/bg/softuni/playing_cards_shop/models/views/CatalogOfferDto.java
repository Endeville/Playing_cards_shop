package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class CatalogOfferDto {
    private Long id;
    private String title;
    private String description;
    private String picture;

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

    public String getPicture() {
        return picture;
    }

    public CatalogOfferDto setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
