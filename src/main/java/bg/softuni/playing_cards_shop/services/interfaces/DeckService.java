package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import bg.softuni.playing_cards_shop.models.views.DeckDetailsDto;

import java.util.List;

public interface DeckService {
    List<CatalogDeckDto> getApprovedDecks();

    DeckDetailsDto findDeckDetailsById(long id);

    Integer getRecommendedPriceForDeckWithId(long id);

    DeckEntity findDeckByTitle(String title);

    List<String> getAllDeckTitles();
}
