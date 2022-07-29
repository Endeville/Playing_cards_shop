package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.DistributorEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DeckRepository extends JpaRepository<DeckEntity, Long> {
    Set<DeckEntity> getDeckEntityByApproved(boolean approved);

    Set<DeckEntity> getDeckEntitiesByApprovedAndTitleContainingIgnoreCase(boolean approved, String title, Sort sort);

    Optional<DeckEntity> findDeckEntityByTitle(String title);

    boolean existsByTitle(String title);
}
