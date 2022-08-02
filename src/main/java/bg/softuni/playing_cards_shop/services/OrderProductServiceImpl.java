package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.repositories.OrderPorductRepository;
import bg.softuni.playing_cards_shop.services.interfaces.OrderProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderPorductRepository orderPorductRepository;

    public OrderProductServiceImpl(OrderPorductRepository orderPorductRepository) {
        this.orderPorductRepository = orderPorductRepository;
    }

    @Override
    public List<OrderProductEntity> saveAll(Set<OrderProductEntity> cart) {
        return this.orderPorductRepository.saveAll(cart);
    }

    @Override
    public List<OrderProductEntity> findOrderProductsForCurrentUser(UserEntity user) {
        return this.orderPorductRepository.findOrderProductEntitiesByOfferSeller(user);
    }
}
