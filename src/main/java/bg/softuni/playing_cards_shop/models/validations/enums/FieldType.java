package bg.softuni.playing_cards_shop.models.validations.enums;

import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;

public enum FieldType {
    EMAIL(UserService.class, "email"),
    USERNAME(UserService.class, "username"),
    TITLE(DeckService.class, "title");

    private final Class<?> entityHandler;
    private final String fieldName;

    FieldType(Class<?> entityHandler, String name) {
        this.entityHandler = entityHandler;
        this.fieldName = name;
    }

    public Class<?> getEntityHandler() {
        return entityHandler;
    }

    public String getFieldName() {
        return fieldName;
    }
}
