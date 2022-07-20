package bg.softuni.playing_cards_shop.models.views;

import java.util.List;

public class CreatorDetailsDto {
    private String name;
    private String description;
    private List<String> pictures;

    public String getName() {
        return name;
    }

    public CreatorDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreatorDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public CreatorDetailsDto setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }
}
