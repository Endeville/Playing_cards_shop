package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.CatalogDeckDto;
import bg.softuni.playing_cards_shop.repositories.DeckRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeckServiceImpl implements DeckService {

    private final DeckRepository deckRepository;
    private final ModelMapper modelMapper;

    public DeckServiceImpl(DeckRepository deckRepository, ModelMapper modelMapper) {
        this.deckRepository = deckRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<CatalogDeckDto> getAccessibleDecks() {
        return deckRepository.getDeckEntityByAccessible(true).stream()
                .map(e->modelMapper.map(e, CatalogDeckDto.class))
                .collect(Collectors.toSet());
    }
}
