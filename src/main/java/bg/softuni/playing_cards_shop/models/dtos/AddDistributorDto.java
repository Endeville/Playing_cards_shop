package bg.softuni.playing_cards_shop.models.dtos;

import bg.softuni.playing_cards_shop.models.validations.UniqueField;
import bg.softuni.playing_cards_shop.models.validations.enums.FieldType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class AddDistributorDto {
    @NotBlank(message = "")
    @Size(min=2, max=60, message = "Please fill in the brand(up to 60 characters).")
    @UniqueField(fieldType = FieldType.DISTRIBUTOR_BRAND)
    private String brand;

    @NotBlank(message = "")
    @Pattern(regexp = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})", message = "Please enter a valid url.")
    private String siteUrl;

    @NotBlank(message = "Please give a brief description of the distributor.")
    private String description;

    private MultipartFile picture;

    public String getBrand() {
        return brand;
    }

    public AddDistributorDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public AddDistributorDto setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddDistributorDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public AddDistributorDto setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
