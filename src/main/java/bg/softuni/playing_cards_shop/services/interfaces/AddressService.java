package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddAddressDto;

import java.util.List;

public interface AddressService {
    void addAddress(AddAddressDto address, String name);

    void deleteAddressById(Long id);
}
