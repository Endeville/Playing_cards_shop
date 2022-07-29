package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddDeckDto;
import bg.softuni.playing_cards_shop.models.dtos.EditDeckDto;
import bg.softuni.playing_cards_shop.models.entities.DeckEntity;
import bg.softuni.playing_cards_shop.models.views.CatalogDeckDto;
import bg.softuni.playing_cards_shop.models.views.DeckDetailsDto;
import bg.softuni.playing_cards_shop.models.views.DeckInfoDto;

import java.io.IOException;
import java.util.List;

public interface DeckService {
    List<CatalogDeckDto> getApprovedDecksByKeyword(String search, String sort);

    DeckDetailsDto findDeckDetailsById(long id);

    DeckEntity findDeckByTitle(String title);

    List<String> getAllDeckTitles();

    void addDeck(AddDeckDto addDeckDto) throws IOException;

    boolean titleExists(String title);

    void editDeck(Long id, EditDeckDto editDeckDto) throws IOException;

    DeckInfoDto findDeckInfoById(Long id);

    void updateRecommendedPrices();
}
