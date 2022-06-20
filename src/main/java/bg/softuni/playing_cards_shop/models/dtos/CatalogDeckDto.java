package bg.softuni.playing_cards_shop.models.dtos;

import java.util.Set;

public class CatalogDeckDto {
    private String title;
    private Set<PictureDto> pictures;

    public CatalogDeckDto() {
    }

    public String getTitle() {
        return title;
    }

    public CatalogDeckDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<PictureDto> getPictures() {
        return pictures;
    }

    public CatalogDeckDto setPictures(Set<PictureDto> pictures) {
        this.pictures = pictures;
        return this;
    }
}
