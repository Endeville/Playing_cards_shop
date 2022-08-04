package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddReviewDto;
import bg.softuni.playing_cards_shop.models.entities.ReviewEntity;

public interface ReviewService {
    ReviewEntity addReview(AddReviewDto review);
}
