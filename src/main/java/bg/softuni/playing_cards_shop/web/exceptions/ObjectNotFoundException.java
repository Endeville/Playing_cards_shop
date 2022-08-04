package bg.softuni.playing_cards_shop.web.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ObjectNotFoundException extends RuntimeException {

    private final String objectName;

    public ObjectNotFoundException(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }
}
