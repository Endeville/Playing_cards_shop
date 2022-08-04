package bg.softuni.playing_cards_shop.models.views;

public class DeckIdentificationDto {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public DeckIdentificationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DeckIdentificationDto setTitle(String title) {
        this.title = title;
        return this;
    }
}
