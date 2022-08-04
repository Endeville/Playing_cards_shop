package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.services.interfaces.WishlistItemService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistItemService wishlistItemService;

    public WishlistController(WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }


    @GetMapping
    public String showWishlist(@RequestParam(name="sort", required = false, defaultValue = "deck.title") String sort,
                               @RequestParam(name = "search", required = false, defaultValue = "") String search,
                               Model model, @AuthenticationPrincipal UserDetails principal){
        model.addAttribute("decks", this.wishlistItemService.getCurrentUserWishlistByKeyword(principal, search, sort));
        model.addAttribute("showSearch", true);

        return "wishlist";
    }


}
