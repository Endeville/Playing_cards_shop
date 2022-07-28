package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddAddressDto;
import bg.softuni.playing_cards_shop.models.entities.AddressEntity;
import bg.softuni.playing_cards_shop.repositories.AddressRepository;
import bg.softuni.playing_cards_shop.services.interfaces.AddressService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper, UserService userService) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addAddress(AddAddressDto addressDto, String name) {
        var address=this.modelMapper.map(addressDto, AddressEntity.class);
        address=this.addressRepository.save(address);

        this.userService.addAddress(name, address);
    }

    @Override
    public void deleteAddressById(Long id) {
        this.addressRepository.deleteById(id);
    }
}
