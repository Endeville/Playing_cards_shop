package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.OrderService;
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

    public VictoryWebSecurityExpressionHandler(OfferService offerService, OrderService orderService) {
        this.offerService = offerService;
        this.orderService = orderService;
    }

    @Override
    protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {

        var root=new OwnerWebSecurityExpressionRoot(authentication, fi, offerService, orderService);

        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}
