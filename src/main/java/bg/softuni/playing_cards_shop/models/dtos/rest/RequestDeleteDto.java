package bg.softuni.playing_cards_shop.models.dtos.rest;


import javax.validation.constraints.NotNull;

public class RequestDeleteDto {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public RequestDeleteDto setId(Long id) {
        this.id = id;
        return this;
    }
}
