package bg.softuni.playing_cards_shop.models.dtos;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class EditOfferDto {

    @NotBlank(message = "Please fill in a title.")
    private String deckTitle;

    @Positive(message = "Please enter a valid price(in euro).")
    private BigDecimal price;

    @Positive(message = "Please select valid quantity.")
    @Max(40)
    private Integer quantity;

    @NotBlank(message = "Description is compulsory(product condition, signatures, number etc.)")
    private String description;

    @NotEmpty(message = "Please provide some pictures of the deck you are offering.")
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
