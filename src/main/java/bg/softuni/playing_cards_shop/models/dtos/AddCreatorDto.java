package bg.softuni.playing_cards_shop.models.dtos;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class AddCreatorDto {
    //todo: field validation
    @NotBlank(message = "")
    @Size(min=2, max=100, message = "Please fill in the name of the creator(up to 100 characters).")
    private String name;

    @NotBlank(message = "Please give a brief description of the creator.")
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
