package bg.softuni.playing_cards_shop.web.exceptions;

public class ItemNotCreatedException extends RuntimeException{
    private final String objectName;

    public ItemNotCreatedException(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }
}
