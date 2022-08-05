package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class OwnerWebSecurityExpressionRoot extends WebSecurityExpressionRoot {


    private final OfferService offerService;
    private final OrderService orderService;

    public OwnerWebSecurityExpressionRoot(Authentication a, FilterInvocation fi, OfferService offerService, OrderService orderService) {
        super(a, fi);
        this.offerService = offerService;
        this.orderService = orderService;
    }

    public boolean isOwner(Long id){
        if(authentication.getPrincipal()==null){
            return false;
        }

        return offerService.isOwner(authentication.getName(), id);
    }

    public boolean isSeller(Long id){
        if(authentication.getPrincipal()==null){
            return false;
        }

        return this.orderService.isSeller(authentication.getName(), id);
    }
}
