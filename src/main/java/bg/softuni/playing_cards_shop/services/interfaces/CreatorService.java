package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;

import java.io.IOException;
import java.util.List;

public interface CreatorService {
    void addCreator(AddCreatorDto creatorDto) throws IOException;

    List<String> getCreatorsNames();
}
