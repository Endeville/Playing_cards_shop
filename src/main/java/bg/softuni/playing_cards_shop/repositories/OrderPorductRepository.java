package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPorductRepository extends JpaRepository<OrderProductEntity, Long> {

    List<OrderProductEntity> findOrderProductEntitiesByOfferSeller(UserEntity offer_seller);
}
