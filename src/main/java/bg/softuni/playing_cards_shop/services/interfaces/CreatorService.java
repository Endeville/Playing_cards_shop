package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.models.views.CreatorDetailsDto;

import java.io.IOException;
import java.util.List;

public interface CreatorService {
    void addCreator(AddCreatorDto creatorDto) throws IOException;

    List<String> getCreatorsNames();

    CreatorEntity findByName(String creatorName);

    boolean nameExists(String name);

    CreatorDetailsDto getCreatorDetailsByName(String name);
}
