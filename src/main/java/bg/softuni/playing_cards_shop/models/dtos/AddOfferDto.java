package bg.softuni.playing_cards_shop.models.dtos;

import bg.softuni.playing_cards_shop.models.validations.NotEmptyFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class AddOfferDto {

    @NotBlank(message = "Please fill in a title.")
    private String deckTitle;

    @NotNull(message = "Please enter a valid price(in Euro)")
    @Positive(message = "Please enter a valid price(in Euro).")
    private BigDecimal price;

    @NotNull(message = "Please enter valid quantity")
    @Positive(message = "Please select valid quantity.")
    private Integer quantity;

    @NotBlank(message = "Description is compulsory.")
    private String description;

    @NotEmptyFile(message = "Please provide a picture.")
    private MultipartFile picture;

    public String getDeckTitle() {
        return deckTitle;
    }

    public AddOfferDto setDeckTitle(String deckTitle) {
        this.deckTitle = deckTitle;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public AddOfferDto setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public AddOfferDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
