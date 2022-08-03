package bg.softuni.playing_cards_shop.web.exceptions;

public class InappropriateLanguageException extends RuntimeException{
    public InappropriateLanguageException(String message) {
        super(message);
    }
}
