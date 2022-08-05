package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class VictoryMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    private final OfferService offerService;
    private final OrderService orderService;
    private final CartProductService cartProductService;
    private final AddressService addressService;
    private final WishlistItemService wishlistItemService;
    private final UserService userService;

    public VictoryMethodSecurityConfiguration(OfferService offerService, OrderService orderService, CartProductService cartProductService, AddressService addressService, WishlistItemService wishlistItemService, UserService userService) {
        this.offerService = offerService;
        this.orderService = orderService;
        this.cartProductService = cartProductService;
        this.addressService = addressService;
        this.wishlistItemService = wishlistItemService;
        this.userService = userService;
    }


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new VictorySecurityExpressionHandler(offerService, orderService, cartProductService, addressService, wishlistItemService, userService);
    }
}
