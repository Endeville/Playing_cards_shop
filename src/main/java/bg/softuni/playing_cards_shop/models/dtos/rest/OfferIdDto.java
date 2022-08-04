package bg.softuni.playing_cards_shop.models.dtos.rest;

import javax.validation.constraints.NotNull;

public class OfferIdDto {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public OfferIdDto setId(Long id) {
        this.id = id;
        return this;
    }
}
