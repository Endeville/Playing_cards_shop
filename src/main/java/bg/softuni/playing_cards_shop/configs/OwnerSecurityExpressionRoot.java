package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.OrderService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class OwnerSecurityExpressionRoot
            extends SecurityExpressionRoot
            implements MethodSecurityExpressionOperations {

    private final Authentication authentication;
    private final OfferService offerService;
    private final OrderService orderService;
    private Object filterObject;
    private Object returnObject;


    public OwnerSecurityExpressionRoot(Authentication authentication, OfferService offerService, OrderService orderService) {
        super(authentication);
        this.authentication=authentication;
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

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject=filterObject;
    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject=returnObject;
    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }
}
