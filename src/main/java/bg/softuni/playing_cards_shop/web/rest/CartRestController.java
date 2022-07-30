package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.OfferIdDto;
import bg.softuni.playing_cards_shop.models.views.rest.CartProductEssentialsDto;
import bg.softuni.playing_cards_shop.services.interfaces.CartProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    private final CartProductService cartProductService;

    public CartRestController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CartProductEssentialsDto> addToCart(@RequestBody OfferIdDto offerIdDto,
                                                              @AuthenticationPrincipal UserDetails principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        var cartProduct=this.cartProductService.addProduct(offerIdDto, principal);

        return ResponseEntity
                .ok()
                .body(cartProduct);
    }

    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> deleteFromCart(@RequestBody OfferIdDto offerIdDto,
                                                                   @AuthenticationPrincipal UserDetails principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        this.cartProductService.deleteProduct(offerIdDto);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
