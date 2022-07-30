package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class CatalogDeckDto {
    private Long id;
    private String title;
    private List<String> pictures;

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

    public List<String> getPictures() {
        return pictures;
    }

    public CatalogDeckDto setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }
}
