package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;

import java.io.IOException;

public interface CreatorService {
    void addCreator(AddCreatorDto creatorDto) throws IOException;
}
