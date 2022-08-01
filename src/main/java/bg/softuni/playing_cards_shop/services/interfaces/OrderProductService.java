package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;

import java.util.Set;

public interface OrderProductService {
    void saveAll(Set<OrderProductEntity> cart);
}
