package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.OfferEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    @Query("SELECT o from OfferEntity o " +
            "where (o.status=bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus.AVAILABLE " +
            "or o.status=bg.softuni.playing_cards_shop.models.entities.enums.OfferStatus.LIMITED) " +
            "and o.deck.title like %:search% " +
            "and o.seller.username like %:seller%")
    List<OfferEntity> findOfferEntityByStatusApprovedOrLimited(@Param(value="search") String search, @Param(value="seller") String seller, Sort sort);
}
