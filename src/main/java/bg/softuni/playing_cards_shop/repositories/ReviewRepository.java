package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
