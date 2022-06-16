package bg.softuni.playing_cards_shop.models.validations.enums;

public enum FieldType {
    EMAIL("email"),
    USERNAME("username");

    private final String name;

    FieldType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
