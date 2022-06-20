package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.CatalogDeckDto;
import bg.softuni.playing_cards_shop.models.entities.DeckEntity;

import java.util.Set;

public interface DeckService {
    Set<CatalogDeckDto> getApprovedDecks();
}
