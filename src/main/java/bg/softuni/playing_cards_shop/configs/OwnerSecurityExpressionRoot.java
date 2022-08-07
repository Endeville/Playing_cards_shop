package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OwnerSecurityExpressionRoot
            extends SecurityExpressionRoot
            implements MethodSecurityExpressionOperations {

    private final Authentication authentication;
    private final OfferService offerService;
    private final OrderService orderService;
    private final CartProductService cartProductService;
    private final AddressService addressService;
    private final UserService userService;
    private Object filterObject;
    private Object returnObject;


    public OwnerSecurityExpressionRoot(Authentication authentication, OfferService offerService, OrderService orderService, CartProductService cartProductService, AddressService addressService, UserService userService) {
        super(authentication);
        this.authentication=authentication;
        this.offerService = offerService;
        this.orderService = orderService;
        this.cartProductService = cartProductService;
        this.addressService = addressService;
        this.userService = userService;
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

    public boolean ownsAddress(Long id){
        if(authentication.getPrincipal()==null){
            return false;
        }

        return this.addressService.ownsAddress(authentication.getName(), id);
    }

    public boolean ownsProfile(Long id){
        if(authentication.getPrincipal()==null){
            return false;
        }

        return this.userService.ownsProfile(authentication.getName(), id);
    }

    public boolean hasCarted(Long id){
        if(authentication.getPrincipal()==null){
            return false;
        }

        return this.cartProductService.hasCarted(authentication.getName(), id);
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
