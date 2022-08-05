package bg.softuni.playing_cards_shop.models.dtos;

import bg.softuni.playing_cards_shop.models.validations.NotEmptyFile;
import bg.softuni.playing_cards_shop.models.validations.UniqueField;
import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AddDeckDto {
    @NotNull(message = "")
    @Size(min=1, max=100, message = "Please fill in the title of the deck(up to 100 characters).")
    @UniqueField(fieldType = FieldType.DECK_TITLE)
    private String title;

    private String description;

    private String countryOfOrigin;

    private final boolean approved=false;

    @NotBlank(message = "")
    private String distributorBrand;

    @NotBlank(message = "")
    private String creatorName;

    @NotEmpty(message = "")
    private List<String> categories;

    @NotEmptyFile(message = "Please provide a picture.")
    private MultipartFile picture;


    public String getTitle() {
        return title;
    }

    public AddDeckDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddDeckDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public AddDeckDto setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }


    public String getDistributorBrand() {
        return distributorBrand;
    }

    public AddDeckDto setDistributorBrand(String distributorBrand) {
        this.distributorBrand = distributorBrand;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public AddDeckDto setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public AddDeckDto setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public AddDeckDto setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
