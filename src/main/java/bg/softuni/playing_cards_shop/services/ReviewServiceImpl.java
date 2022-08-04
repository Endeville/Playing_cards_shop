package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddReviewDto;
import bg.softuni.playing_cards_shop.models.entities.ReviewEntity;
import bg.softuni.playing_cards_shop.repositories.ReviewRepository;
import bg.softuni.playing_cards_shop.services.interfaces.ReviewService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewEntity addReview(AddReviewDto addReview) {
        var review=this.modelMapper.map(addReview, ReviewEntity.class);
        review.setCreator(this.userService.getCurrentUser());
        review.setCreated(Instant.now());

        return this.reviewRepository.save(review);
    }
}
