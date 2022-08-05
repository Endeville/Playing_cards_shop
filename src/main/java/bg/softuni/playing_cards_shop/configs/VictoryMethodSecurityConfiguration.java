package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import bg.softuni.playing_cards_shop.services.interfaces.OrderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class VictoryMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    private final OfferService offerService;
    private final OrderService orderService;

    public VictoryMethodSecurityConfiguration(OfferService offerService, OrderService orderService) {
        this.offerService = offerService;
        this.orderService = orderService;
    }


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new VictorySecurityExpressionHandler(offerService, orderService);
    }
}
