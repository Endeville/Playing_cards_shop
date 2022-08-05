package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.*;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.stereotype.Component;

@Component(value = "victoryExpressionHandler")
public class VictoryWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

    private final OfferService offerService;
    private final OrderService orderService;
    private final CartProductService cartProductService;
    private final WishlistItemService wishlistItemService;
    private final UserService userService;

    public VictoryWebSecurityExpressionHandler(OfferService offerService, OrderService orderService, CartProductService cartProductService, WishlistItemService wishlistItemService, UserService userService) {
        this.offerService = offerService;
        this.orderService = orderService;
        this.cartProductService = cartProductService;
        this.wishlistItemService = wishlistItemService;
        this.userService = userService;
    }

    @Override
    protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {

        var root=new OwnerWebSecurityExpressionRoot(authentication, fi, offerService, orderService, cartProductService, wishlistItemService, userService);

        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}
