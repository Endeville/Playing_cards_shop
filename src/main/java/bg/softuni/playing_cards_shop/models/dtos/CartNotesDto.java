package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.NotNull;

public class CartNotesDto {
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
