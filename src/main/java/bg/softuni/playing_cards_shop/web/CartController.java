package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.services.interfaces.CartProductService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

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
                           @AuthenticationPrincipal UserDetails userDetails) {
        this.getCartItemsDetails(model, userDetails);

        return "cart";
    }

    private void getCartItemsDetails(Model model, UserDetails userDetails) {

        var cartProducts = this.cartProductService.getCartProductsByCustomer(userDetails.getUsername());
        var addresses = this.userService.findAddressesByUserUsername(userDetails.getUsername());

        var totalPrice = cartProducts.stream()
                .map(p-> p.getOfferPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        model.addAttribute("products", cartProducts);
        model.addAttribute("addresses", addresses);
    }
}
