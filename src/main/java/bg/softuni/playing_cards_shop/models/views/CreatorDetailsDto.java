package bg.softuni.playing_cards_shop.models.views;

public class CreatorDetailsDto {
    private String name;
    private String description;
    private String picture;

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

    public String getPicture() {
        return picture;
    }

    public CreatorDetailsDto setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
