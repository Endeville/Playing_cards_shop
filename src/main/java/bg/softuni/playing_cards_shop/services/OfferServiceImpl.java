package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.dtos.AddReviewDto;
import bg.softuni.playing_cards_shop.models.dtos.EditOfferDto;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.entities.ReviewEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus;
import bg.softuni.playing_cards_shop.models.views.CatalogOfferDto;
import bg.softuni.playing_cards_shop.models.views.OfferDetailsDto;
import bg.softuni.playing_cards_shop.models.views.OfferInfoDto;
import bg.softuni.playing_cards_shop.models.views.ReviewDetailsDto;
import bg.softuni.playing_cards_shop.repositories.OfferRepository;
import bg.softuni.playing_cards_shop.services.interfaces.*;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_OFFER;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final DeckService deckService;
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, PictureService pictureService, UserService userService, DeckService deckService, ReviewService reviewService, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.deckService = deckService;
        this.reviewService = reviewService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(AddOfferDto addOfferDto) throws IOException {
        var offer=this.modelMapper.map(addOfferDto, OfferEntity.class);

        if (this.pictureService.validatePicture(addOfferDto.getPicture())) {
            var picture = this.pictureService.save(addOfferDto.getPicture());
            offer.setPicture(picture);
        } else {
            throw new IllegalStateException("No pictures provided");
        }

        offer.setStatus(OfferStatus.PENDING)
                .setSeller(this.userService.getCurrentUser())
                .setDeck(deckService.findDeckByTitle(addOfferDto.getDeckTitle()))
                .setPrice(addOfferDto.getPrice())
                .setReviews(new HashSet<>())
                .setQuantity(addOfferDto.getQuantity())
                .setDescription(addOfferDto.getDescription());

        this.offerRepository.save(offer);
    }

    @Override
    public List<CatalogOfferDto> getActiveOffersByKeyword(String search, String sort) {
        return this.offerRepository.findOfferEntityByStatusApprovedOrLimited(search, Sort.by(sort)).stream()
                .map((o)->{
                    var offer=this.modelMapper.map(o, CatalogOfferDto.class);
                    offer.setTitle(o.getDeck().getTitle());
                    offer.setPicture(this.pictureService.getPictureUrl(o.getPicture()));

                    return offer;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OfferDetailsDto getOfferDetailsById(Long id) {
        var offer = this.offerRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_OFFER));
        var result=this.modelMapper.map(offer, OfferDetailsDto.class);

        result.setPicture(this.pictureService.getPictureUrl(offer.getPicture()));
        result.setCarted(this.userService.currentUserHasCarted(offer));

        return result;
    }

    @Override
    public OfferEntity findOfferById(Long id) {
        return this.offerRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_OFFER));
    }

    @Override
    public void editOffer(Long id, EditOfferDto editOfferDto) throws IOException {
        var offer = this.offerRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_OFFER));

        this.pictureService.deletePicture(offer.getPicture());

        if (this.pictureService.validatePicture(editOfferDto.getPicture())) {
            var picture = this.pictureService.save(editOfferDto.getPicture());
            offer.setPicture(picture);
        } else {
            throw new IllegalStateException("No pictures provided");
        }

        offer.setStatus(OfferStatus.PENDING)
                .setSeller(this.userService.getCurrentUser())
                .setDeck(deckService.findDeckByTitle(editOfferDto.getDeckTitle()))
                .setPrice(editOfferDto.getPrice())
                .setReviews(new HashSet<>())
                .setQuantity(editOfferDto.getQuantity())
                .setDescription(editOfferDto.getDescription());


        //todo: add the deck for mod approval
        this.offerRepository.save(offer);
    }

    @Override
    public OfferInfoDto getOfferInfoById(Long id) {
        var offer = this.offerRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_OFFER));
        var result=this.modelMapper.map(offer, OfferInfoDto.class);

        result.setPicture(this.pictureService.getPictureUrl(offer.getPicture()));

        return result;
    }

    @Override
    public List<ReviewDetailsDto> findReviewsByOfferId(Long id) {
        return this.offerRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_OFFER))
                .getReviews().stream()
                .map(r-> this.modelMapper.map(r, ReviewDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addReviewToOfferById(Long id, AddReviewDto review) {
        var offer=this.offerRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_OFFER));

        var reviewToAdd=this.reviewService.addReview(review);
        var reviews=offer.getReviews();
        reviews.add(reviewToAdd);

        offer.setReviews(reviews);

        this.offerRepository.save(offer);
    }

    @Override
    public boolean offerHasBeenRatedByUser(Long id, UserDetails principal) {
        var user=this.userService.getCurrentUser();
        return offerRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_OFFER))
                .getReviews()
                .stream().map(ReviewEntity::getCreator)
                .anyMatch(c-> c.equals(user));
    }
}
