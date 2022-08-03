package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddRequestDto;

public interface RequestService {
    void addRequest(AddRequestDto requestDto);
}
