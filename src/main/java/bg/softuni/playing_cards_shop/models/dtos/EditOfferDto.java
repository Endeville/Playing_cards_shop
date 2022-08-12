package bg.softuni.playing_cards_shop.models.dtos;

import bg.softuni.playing_cards_shop.models.validations.NotEmptyFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class EditOfferDto {

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

    private MultipartFile picture;

    public String getDeckTitle() {
        return deckTitle;
    }

    public EditOfferDto setDeckTitle(String deckTitle) {
        this.deckTitle = deckTitle;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public EditOfferDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public EditOfferDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EditOfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public EditOfferDto setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
