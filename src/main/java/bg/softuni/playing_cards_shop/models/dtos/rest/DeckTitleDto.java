package bg.softuni.playing_cards_shop.models.dtos.rest;

import javax.validation.constraints.NotBlank;

public class DeckTitleDto {
    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public DeckTitleDto setTitle(String title) {
        this.title = title;
        return this;
    }
}
