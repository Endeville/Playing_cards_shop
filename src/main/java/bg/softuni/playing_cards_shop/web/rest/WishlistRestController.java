package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.DeckTitleDto;
import bg.softuni.playing_cards_shop.models.views.rest.WishlistItemDto;
import bg.softuni.playing_cards_shop.services.interfaces.WishlistItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistRestController {

    private final WishlistItemService wishlistService;

    public WishlistRestController(WishlistItemService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping(value = "/like", consumes = "application/json", produces = "application/json")
    public ResponseEntity<WishlistItemDto> like(@Valid @RequestBody DeckTitleDto deckTitleDto, @AuthenticationPrincipal UserDetails principal) {
        if(wishlistService.hasLiked(principal.getUsername(), deckTitleDto.getTitle())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        var wishlist=this.wishlistService.like(deckTitleDto.getTitle());

        return ResponseEntity
                .ok()
                .body(new WishlistItemDto()
                        .setDeckTitle(wishlist.getDeck().getTitle())
                        .setUserUsername(wishlist.getUser().getUsername()));
    }


    @DeleteMapping(value = "/dislike", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> dislike(@Valid @RequestBody DeckTitleDto deckTitleDto, @AuthenticationPrincipal UserDetails principal) {

        if(!wishlistService.hasLiked(principal.getUsername(), deckTitleDto.getTitle())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        this.wishlistService.dislike(deckTitleDto.getTitle());

        return ResponseEntity
                .ok()
                .build();
    }
}
