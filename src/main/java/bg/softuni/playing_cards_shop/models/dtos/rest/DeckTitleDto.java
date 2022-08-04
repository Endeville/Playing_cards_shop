package bg.softuni.playing_cards_shop.models.dtos.rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeckTitleDto {

    @NotNull(message = "")
    @Size(min=1, max=100, message = "Please fill in the title of the deck(up to 100 characters).")
    private String title;

    public String getTitle() {
        return title;
    }

    public DeckTitleDto setTitle(String title) {
        this.title = title;
        return this;
    }
}
