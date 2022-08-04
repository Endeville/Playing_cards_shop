package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {

    @Transactional
    void deleteByCustomerIdAndOfferId(Long customerId, Long offerId);

    List<CartProductEntity> findCartProductEntitiesByCustomerUsername(String customerUsername);

    Optional<CartProductEntity> findCartProductEntityByOfferIdAndCustomerId(Long offer_id, Long customer_id);
}
