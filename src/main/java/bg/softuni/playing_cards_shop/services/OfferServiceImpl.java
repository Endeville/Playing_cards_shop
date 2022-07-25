package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.dtos.EditOfferDto;
import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus;
import bg.softuni.playing_cards_shop.models.views.CatalogOfferDto;
import bg.softuni.playing_cards_shop.models.views.OfferDetailsDto;
import bg.softuni.playing_cards_shop.models.views.OfferInfoDto;
import bg.softuni.playing_cards_shop.repositories.OfferRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_DECK;
import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_OFFER;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final DeckService deckService;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, PictureService pictureService, UserService userService, DeckService deckService, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.deckService = deckService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(AddOfferDto addOfferDto) throws IOException {
        var offer=this.modelMapper.map(addOfferDto, OfferEntity.class);

        if (this.pictureService.validatePictures(addOfferDto.getPictures())) {
            var pictures = this.pictureService.saveAll(addOfferDto.getPictures());
            offer.setPictures(pictures);
        } else {
            throw new IllegalStateException("No pictures provided");
        }

        offer.setStatus(OfferStatus.PENDING)
                .setSeller(userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()))
                .setDeck(deckService.findDeckByTitle(addOfferDto.getDeckTitle()))
                .setPrice(addOfferDto.getPrice())
                .setReviews(new HashSet<>())
                .setQuantity(addOfferDto.getQuantity())
                .setDescription(addOfferDto.getDescription());

        this.offerRepository.save(offer);
    }

    @Override
    public List<CatalogOfferDto> getActiveOffers() {
        return this.offerRepository.findOfferEntityByStatusApprovedOrLimited().stream()
                .map((o)->{
                    var offer=this.modelMapper.map(o, CatalogOfferDto.class);
                    offer.setTitle(o.getDeck().getTitle());
                    offer.setPictures(this.pictureService.getPicturesUrls(o.getPictures()));

                    return offer;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OfferDetailsDto getOfferDetailsById(Long id) {
        var offer = this.offerRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_OFFER));
        var result=this.modelMapper.map(offer, OfferDetailsDto.class);

        result.setPictures(this.pictureService.getPicturesUrls(offer.getPictures()));

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

        this.pictureService.deletePictures(offer.getPictures());

        if (this.pictureService.validatePictures(editOfferDto.getPictures())) {
            var pictures = this.pictureService.saveAll(editOfferDto.getPictures());
            offer.setPictures(pictures);
        } else {
            throw new IllegalStateException("No pictures provided");
        }

        offer.setStatus(OfferStatus.PENDING)
                .setSeller(userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()))
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

        result.setPictures(this.pictureService.getPicturesUrls(offer.getPictures()));

        return result;
    }
}
