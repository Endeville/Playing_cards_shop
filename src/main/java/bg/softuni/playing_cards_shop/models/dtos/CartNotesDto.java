package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CartNotesDto {
    @Size(max = 100, message = "The notes should be up to 100 characters long")
    private String notes;

    @NotNull(message = "Please provide a delivery address.")
    private Long addressId;

    public String getNotes() {
        return notes;
    }

    public CartNotesDto setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Long getAddressId() {
        return addressId;
    }

    public CartNotesDto setAddressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }
}
