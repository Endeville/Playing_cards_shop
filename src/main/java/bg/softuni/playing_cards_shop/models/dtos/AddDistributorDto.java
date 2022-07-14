package bg.softuni.playing_cards_shop.models.dtos;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class AddDistributorDto {
    @NotBlank(message = "")
    @Size(min=2, max=60, message = "The brand should have between 2 and 60 characters.")
    private String brand;

    @NotBlank(message = "")
//    @Pattern(regexp = "", message = "Please enter a valid url.")
    private String siteUrl;

    @NotBlank(message = "Please give a description of the distributor.")
    private String description;

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
