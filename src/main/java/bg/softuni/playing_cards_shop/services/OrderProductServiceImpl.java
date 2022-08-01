package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.repositories.OrderPorductRepository;
import bg.softuni.playing_cards_shop.services.interfaces.OrderProductService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderPorductRepository orderPorductRepository;

    public OrderProductServiceImpl(OrderPorductRepository orderPorductRepository) {
        this.orderPorductRepository = orderPorductRepository;
    }

    @Override
    public void saveAll(Set<OrderProductEntity> cart) {
        this.orderPorductRepository.saveAll(cart);
    }
}
