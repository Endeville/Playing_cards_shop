package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    @Query("SELECT o from OfferEntity o where o.status=bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus.APPROVED " +
            "or o.status=bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus.LIMITED")
    List<OfferEntity> findOfferEntityByStatusApprovedOrLimited();
}
