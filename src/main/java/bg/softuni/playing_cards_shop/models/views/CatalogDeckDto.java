package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class CatalogDeckDto {
    private Long id;
    private String title;
    private String picture;

    public CatalogDeckDto() {
    }

    public Long getId() {
        return id;
    }

    public CatalogDeckDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CatalogDeckDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CatalogDeckDto setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
