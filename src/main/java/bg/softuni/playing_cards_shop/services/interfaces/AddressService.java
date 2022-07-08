package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddressAddDto;

public interface AddressService {
    void addAddress(AddressAddDto address, String name);
}
