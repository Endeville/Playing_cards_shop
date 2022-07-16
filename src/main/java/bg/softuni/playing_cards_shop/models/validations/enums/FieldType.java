package bg.softuni.playing_cards_shop.models.validations.enums;

public enum FieldType {
    EMAIL("User", "email"),
    USERNAME("User", "username"),
    TITLE("Deck", "title");

    private final String entity;
    private final String name;

    FieldType(String entity, String name) {
        this.entity = entity;
        this.name = name;
    }

    public String getEntity() {
        return entity;
    }

    public String getName() {
        return name;
    }
}
