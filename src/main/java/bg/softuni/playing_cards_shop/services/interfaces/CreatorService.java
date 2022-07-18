package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CreatorService {
    void addCreator(AddCreatorDto creatorDto) throws IOException;

    List<String> getCreatorsNames();

    Optional<CreatorEntity> findByName(String creatorName);

    boolean nameExists(String name);
}
