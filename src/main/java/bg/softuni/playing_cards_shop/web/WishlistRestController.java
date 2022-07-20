package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.WishlistItemEntity;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.services.interfaces.WishlistItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class WishlistRestController {

    private final WishlistItemService wishlistService;

    public WishlistRestController(WishlistItemService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/like")
    @ResponseBody
    public ResponseEntity<WishlistItemEntity> like(@RequestParam(name = "deckTitle") String title, @AuthenticationPrincipal UserDetails principal) {
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        var wishlist=this.wishlistService.like(principal, title);

        return ResponseEntity
                .ok()
                .body(wishlist);
    }
}
