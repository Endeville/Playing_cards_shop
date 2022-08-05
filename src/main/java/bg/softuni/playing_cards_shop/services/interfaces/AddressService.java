package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddAddressDto;
import bg.softuni.playing_cards_shop.models.entities.AddressEntity;

public interface AddressService {

    String OBJECT_NAME_ADDRESS="address";

    void addAddress(AddAddressDto address, String name);

    void deleteAddressById(Long id);

    AddressEntity findAddressById(Long addressId);

    boolean ownsAddress(String name, Long id);
}
