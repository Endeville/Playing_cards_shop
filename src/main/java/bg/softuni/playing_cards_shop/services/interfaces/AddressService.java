package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddAddressDto;
import bg.softuni.playing_cards_shop.models.entities.AddressEntity;

public interface AddressService {
    void addAddress(AddAddressDto address, String name);

    void deleteAddressById(Long id);

    AddressEntity findAddressById(Long addressId);
}
