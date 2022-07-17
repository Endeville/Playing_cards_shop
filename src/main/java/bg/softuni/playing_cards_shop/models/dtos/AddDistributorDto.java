package bg.softuni.playing_cards_shop.models.dtos;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class AddDistributorDto {
    @NotBlank(message = "")
    @Size(min=2, max=60, message = "Please fill in the brand(up to 60 characters).")
    private String brand;

    @NotBlank(message = "")
    //todo: site url regex validation(or something else)
//    @Pattern(regexp = "", message = "Please enter a valid url.")
    private String siteUrl;

    @NotBlank(message = "Please give a brief description of the distributor.")
    private String description;

    @NotEmpty(message = "Please upload pictures for this distributor.")
    private List<MultipartFile> pictures;

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

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public AddDistributorDto setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
        return this;
    }
}
