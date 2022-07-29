package bg.softuni.playing_cards_shop.models.dtos;

import bg.softuni.playing_cards_shop.models.validations.UniqueField;
import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class EditDeckDto {
    @NotNull(message = "")
    @Size(min=1, max=100, message = "Please fill in the title of the deck(up to 100 characters).")
    @UniqueField(fieldType = FieldType.DECK_TITLE)
    private String title;

    private String description;

    private String countryOfOrigin;

    @NotBlank(message = "")
    private String distributorBrand;

    @NotBlank(message = "")
    private String creatorName;

    @NotEmpty(message = "")
    private List<String> categories;

    @NotEmpty(message = "Please upload pictures for this deck.")
    private List<MultipartFile> pictures;


    public String getTitle() {
        return title;
    }

    public EditDeckDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EditDeckDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public EditDeckDto setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }

    public String getDistributorBrand() {
        return distributorBrand;
    }

    public EditDeckDto setDistributorBrand(String distributorBrand) {
        this.distributorBrand = distributorBrand;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public EditDeckDto setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public EditDeckDto setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public EditDeckDto setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
        return this;
    }
}
