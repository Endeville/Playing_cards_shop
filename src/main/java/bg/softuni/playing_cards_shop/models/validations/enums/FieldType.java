package bg.softuni.playing_cards_shop.models.validations.enums;

import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.DistributorService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;

public enum FieldType {
    USER_EMAIL(UserService.class, "email"),
    USER_USERNAME(UserService.class, "username"),
    DECK_TITLE(DeckService.class, "title"),
    CREATOR_NAME(CreatorService.class, "name"),
    DISTRIBUTOR_BRAND(DistributorService.class, "brand");

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
