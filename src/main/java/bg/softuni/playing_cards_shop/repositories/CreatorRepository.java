package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreatorRepository extends JpaRepository<CreatorEntity, Long> {

    Optional<CreatorEntity> findCreatorEntityByName(String name);

    boolean existsByName(String name);
}
