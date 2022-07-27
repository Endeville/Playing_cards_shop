package bg.softuni.playing_cards_shop.models.dtos;

public class AddReviewDto {
    private short rating;
    private String review;

    public short getRating() {
        return rating;
    }

    public AddReviewDto setRating(short rating) {
        this.rating = rating;
        return this;
    }

    public String getReview() {
        return review;
    }

    public AddReviewDto setReview(String review) {
        this.review = review;
        return this;
    }
}
