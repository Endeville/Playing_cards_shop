package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {

    @Transactional
    void deleteByCustomerIdAndOfferId(Long customer_id, Long offer_id);
}
