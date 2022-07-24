package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.services.interfaces.CartProductService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartProductService cartProductService;
    private final UserService userService;

    public CartController(CartProductService cartProductService, UserService userService) {
        this.cartProductService = cartProductService;
        this.userService = userService;
    }

    @GetMapping
    public String showCart(Model model,
                           @AuthenticationPrincipal UserDetails userDetails){
        this.getCartItemsDetails(model, userDetails);

        return "cart";
    }

    private void getCartItemsDetails(Model model, UserDetails userDetails) {

        this.cartProductService.getCartItemsByCustomer(userDetails.getUsername());
        this.userService.findAddressesByUser(userDetails.getUsername());
    }
}
