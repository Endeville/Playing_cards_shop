package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;

import java.util.List;
import java.util.Set;

public interface OrderProductService {
    List<OrderProductEntity> saveAll(Set<OrderProductEntity> cart);

    List<OrderProductEntity> findOrderProductsForCurrentUser(UserEntity user);
}
