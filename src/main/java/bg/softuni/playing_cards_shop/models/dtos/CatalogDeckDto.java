package bg.softuni.playing_cards_shop.models.dtos;

import java.util.List;
import java.util.Set;

public class CatalogDeckDto {
    private Long id;
    private String title;
    private Set<PictureDto> pictures;

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

    public Set<PictureDto> getPictures() {
        return pictures;
    }

    public CatalogDeckDto setPictures(Set<PictureDto> pictures) {
        this.pictures = pictures;
        return this;
    }
}
