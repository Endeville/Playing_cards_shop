package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.rest.OfferIdDto;
import bg.softuni.playing_cards_shop.models.entities.CartProductEntity;
import bg.softuni.playing_cards_shop.models.views.CartProductDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductEssentialsDto;
import bg.softuni.playing_cards_shop.repositories.CartProductRepository;
import bg.softuni.playing_cards_shop.services.interfaces.CartProductService;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartProductServiceImpl implements CartProductService {

    private final CartProductRepository cartProductRepository;
    private final UserService userService;
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public CartProductServiceImpl(CartProductRepository cartProductRepository, UserService userService, OfferService offerService, ModelMapper modelMapper) {
        this.cartProductRepository = cartProductRepository;
        this.userService = userService;
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartProductDto> getCartProductsByCustomer(String username) {
        return this.cartProductRepository.findCartProductEntitiesByCustomerUsername(username).stream()
                .map(cp->this.modelMapper.map(cp, CartProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartProductEssentialsDto addProduct(OfferIdDto offerIdDto, UserDetails principal) {
        var cartProduct=new CartProductEntity()
                .setCustomer(this.userService.findUserByUsername(principal.getUsername()))
                .setQuantity(1)
                .setOffer(this.offerService.findOfferById(offerIdDto.getId()));

        this.cartProductRepository.save(cartProduct);

        return this.modelMapper.map(cartProduct, CartProductEssentialsDto.class);
    }

    @Override
    public void deleteProduct(OfferIdDto offerIdDto, UserDetails principal) {
        this.cartProductRepository
                .deleteByCustomerIdAndOfferId(this.userService.findUserByUsername(principal.getUsername()).getId(),
                        offerIdDto.getId());
    }
}
