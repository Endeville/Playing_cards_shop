package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class AddReviewDto {
    @Min(value=1, message = "The rating must be at least 1.")
    @Max(value=5, message = "The rating must be maximum 5.")
    private short rating;

    @Size(min=5, message = "Please share your opinion on this offer.")
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
