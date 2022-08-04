package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
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

    @Query("SELECT d from DeckEntity d " +
            "where d.title like %:search% " +
            "and d.creator.name like %:creator% " +
            "and d.distributor.brand like %:distributor%")
    List<DeckEntity> getDeckEntitiesByTitleDistributorOrCreatorContaining(@Param(value="search") String search,
                                                                                       Sort sort,
                                                                                       @Param(value = "distributor") String distributor,
                                                                                       @Param(value = "creator") String creator);

    Optional<DeckEntity> findDeckEntityByTitle(String title);

    boolean existsByTitle(String title);
}
