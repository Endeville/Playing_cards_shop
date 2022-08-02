package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.rest.OfferIdDto;
import bg.softuni.playing_cards_shop.models.entities.CartProductEntity;
import bg.softuni.playing_cards_shop.models.views.CartProductDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductEssentialsDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductPriceQuantityDto;
import bg.softuni.playing_cards_shop.repositories.CartProductRepository;
import bg.softuni.playing_cards_shop.services.interfaces.CartProductService;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
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
                .filter(cp->{
                    if(cp.getQuantity()==0){
                        this.cartProductRepository.delete(cp);
                        return false;
                    }else{
                        return true;
                    }
                })
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
    public void deleteProduct(OfferIdDto offerIdDto) {
        this.cartProductRepository
                .deleteByCustomerIdAndOfferId(this.userService.getCurrentUser().getId(),
                        offerIdDto.getId());
    }

    @Override
    public CartProductPriceQuantityDto changeQuantity(Long offerId, int i) {
        var user=this.userService.getCurrentUser();
        var cartProduct = this.cartProductRepository.findCartProductEntityByOfferIdAndCustomerId(offerId, user.getId())
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_CART_PRODUCT));

        if(cartProduct.getQuantity()==cartProduct.getOffer().getQuantity() && i>=0){
            return getCartProductPriceQuantityDto(cartProduct);
        }

        cartProduct.setQuantity(cartProduct.getQuantity()+i);
        this.cartProductRepository.save(cartProduct);

        return getCartProductPriceQuantityDto(cartProduct);
    }

    private CartProductPriceQuantityDto getCartProductPriceQuantityDto(CartProductEntity cartProduct) {
        var result=this.modelMapper.map(cartProduct, CartProductPriceQuantityDto.class);
        result.setPrice(cartProduct.getOffer().getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())));
        result.setMaxQuantity(cartProduct.getOffer().getQuantity());
        result.setPricePerProduct(cartProduct.getOffer().getPrice());

        return result;
    }

    @Override
    public void deleteCartProducts(Set<CartProductEntity> cart) {
        this.cartProductRepository.deleteAll(cart);
    }
}
