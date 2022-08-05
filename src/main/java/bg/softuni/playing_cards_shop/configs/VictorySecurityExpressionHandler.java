package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.*;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;


public class VictorySecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final OfferService offerService;
    private final OrderService orderService;
    private final CartProductService cartProductService;
    private final AddressService addressService;
    private final WishlistItemService wishlistItemService;
    private final UserService userService;

    public VictorySecurityExpressionHandler(OfferService offerService, OrderService orderService, CartProductService cartProductService, AddressService addressService, WishlistItemService wishlistItemService, UserService userService) {
        this.offerService = offerService;
        this.orderService = orderService;
        this.cartProductService = cartProductService;
        this.addressService = addressService;
        this.wishlistItemService = wishlistItemService;
        this.userService = userService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        OwnerSecurityExpressionRoot root = new OwnerSecurityExpressionRoot(authentication, offerService, orderService, cartProductService, addressService, wishlistItemService, userService);

        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}
