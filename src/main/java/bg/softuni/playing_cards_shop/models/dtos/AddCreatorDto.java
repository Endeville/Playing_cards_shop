package bg.softuni.playing_cards_shop.models.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AddCreatorDto {
    private String name;
    private String description;
    private List<MultipartFile> pictures;

    public AddCreatorDto() {
    }

    public String getName() {
        return name;
    }

    public AddCreatorDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddCreatorDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public AddCreatorDto setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
        return this;
    }
}
