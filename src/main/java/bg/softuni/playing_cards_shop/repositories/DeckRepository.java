package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DeckRepository extends JpaRepository<DeckEntity, Long> {
    Set<DeckEntity> getDeckEntitiesByApproved(boolean approved);

    Set<DeckEntity> getDeckEntitiesByApprovedAndTitleContainingIgnoreCase(boolean approved, String search, Sort sort);

    Optional<DeckEntity> findDeckEntityByTitle(String title);

    boolean existsByTitle(String title);
}
