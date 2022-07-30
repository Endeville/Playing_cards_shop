package bg.softuni.playing_cards_shop.models.views;

import java.time.Instant;

public class ReviewDetailsDto {

    private String review;

    private short rating;

    private Instant created;

    private String creatorUsername;

    public String getReview() {
        return review;
    }

    public ReviewDetailsDto setReview(String review) {
        this.review = review;
        return this;
    }

    public short getRating() {
        return rating;
    }

    public ReviewDetailsDto setRating(short rating) {
        this.rating = rating;
        return this;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public ReviewDetailsDto setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public ReviewDetailsDto setCreated(Instant created) {
        this.created = created;
        return this;
    }
}
