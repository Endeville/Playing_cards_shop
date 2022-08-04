package bg.softuni.playing_cards_shop.web.exceptions;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;

public class InvalidOrderException extends RuntimeException{
    private final String message;
    private final CartNotesDto cartNotesDto;

    public InvalidOrderException(String message, CartNotesDto cartNotesDto) {
        this.message = message;
        this.cartNotesDto = cartNotesDto;
    }

    public String getMessage() {
        return message;
    }

    public CartNotesDto getCartNotesDto() {
        return cartNotesDto;
    }
}
