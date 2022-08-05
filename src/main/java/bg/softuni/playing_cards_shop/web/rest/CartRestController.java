package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.OfferIdDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductEssentialsDto;
import bg.softuni.playing_cards_shop.models.dtos.rest.CartProductUpdateDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductPriceQuantityDto;
import bg.softuni.playing_cards_shop.services.interfaces.CartProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    private final CartProductService cartProductService;

    public CartRestController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @PreAuthorize("!hasCarted(#offerIdDto.id)")
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CartProductEssentialsDto> addToCart(@Valid @RequestBody OfferIdDto offerIdDto,
                                                              @AuthenticationPrincipal UserDetails principal){

        var cartProduct=this.cartProductService.addProduct(offerIdDto, principal);

        return ResponseEntity
                .ok()
                .body(cartProduct);
    }

    @PreAuthorize("hasCarted(#offerIdDto.id)")
    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> deleteFromCart(@Valid @RequestBody OfferIdDto offerIdDto){
        this.cartProductService.deleteProduct(offerIdDto);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PreAuthorize("hasCarted(#cartProductUpdateDto.offerId)")
    @PatchMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CartProductPriceQuantityDto> updateCartProduct(@Valid @RequestBody CartProductUpdateDto cartProductUpdateDto){
        CartProductPriceQuantityDto result;

        if(cartProductUpdateDto.getOperation().equalsIgnoreCase("plus")){
            result=this.cartProductService.changeQuantity(cartProductUpdateDto.getOfferId(), 1);
        }else if(cartProductUpdateDto.getOperation().equalsIgnoreCase("minus")){
            result=this.cartProductService.changeQuantity(cartProductUpdateDto.getOfferId(), -1);
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        return ResponseEntity
                .ok(result);
    }
}
