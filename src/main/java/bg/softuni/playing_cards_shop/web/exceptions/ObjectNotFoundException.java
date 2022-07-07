package bg.softuni.playing_cards_shop.web.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private final String objectName;

    public ObjectNotFoundException(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }
}
