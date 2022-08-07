package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class OwnerWebSecurityExpressionRoot extends WebSecurityExpressionRoot {


    private final OfferService offerService;
    private final OrderService orderService;
    private final CartProductService cartProductService;
    private final UserService userService;
    private final WishlistItemService wishlistItemService;

    public OwnerWebSecurityExpressionRoot(Authentication a, FilterInvocation fi, OfferService offerService, OrderService orderService, CartProductService cartProductService, UserService userService, WishlistItemService wishlistItemService) {
        super(a, fi);
        this.offerService = offerService;
        this.orderService = orderService;
        this.cartProductService = cartProductService;
        this.userService = userService;
        this.wishlistItemService = wishlistItemService;
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

    public boolean hasLiked(String deckTitle){
        if(authentication.getPrincipal()==null){
            return false;
        }

        return this.wishlistItemService.hasLiked(authentication.getName(), deckTitle);
    }
}
