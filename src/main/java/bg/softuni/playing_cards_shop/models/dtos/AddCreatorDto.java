package bg.softuni.playing_cards_shop.models.dtos;

import bg.softuni.playing_cards_shop.models.validations.UniqueField;
import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddCreatorDto {
    @NotBlank(message = "")
    @Size(min=2, max=100, message = "Please fill in the name of the creator(up to 100 characters).")
    @UniqueField(fieldType = FieldType.CREATOR_NAME)
    private String name;

    @NotBlank(message = "Please give a brief description of the creator.")
    private String description;

    //todo: spring adds some tomcat multipart file when this is empty -> fix it
    private MultipartFile picture;

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

    public MultipartFile getPicture() {
        return picture;
    }

    public AddCreatorDto setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
