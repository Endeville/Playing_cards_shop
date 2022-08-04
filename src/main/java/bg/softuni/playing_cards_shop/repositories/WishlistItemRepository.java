package bg.softuni.playing_cards_shop.repositories;

import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.entities.WishlistItemEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItemEntity, Long> {

    Optional<WishlistItemEntity> findWishlistItemEntityByUserAndDeck(UserEntity user, DeckEntity deck);

    List<WishlistItemEntity> findWishlistItemEntitiesByUserUsernameAndDeckTitleContainingIgnoreCase(String username, String search, Sort sort);
}
