package bg.softuni.playing_cards_shop.models.views;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureDto that = (PictureDto) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return this.url;
    }
}
