package bg.softuni.playing_cards_shop.models.cloudinary;

public class CloudinaryImage {

    private String url;
    private String publicId;

    public String getUrl() {
        return url;
    }

    public CloudinaryImage setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public CloudinaryImage setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
