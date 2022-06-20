package bg.softuni.playing_cards_shop.models.dtos;

public class PictureDto {
    private String url;

    public PictureDto() {
    }

    public String getUrl() {
        return url;
    }

    public PictureDto setUrl(String url) {
        this.url = url;
        return this;
    }
}
