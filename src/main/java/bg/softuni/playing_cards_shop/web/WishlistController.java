package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.services.interfaces.WishlistItemService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistItemService wishlistItemService;

    public WishlistController(WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }


    @GetMapping
    public String showWishlist(Model model, @AuthenticationPrincipal UserDetails principal){
        model.addAttribute("decks", this.wishlistItemService.getCurrentUserWishlist(principal));

        return "wishlist";
    }
}
