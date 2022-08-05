package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddAddressDto;
import bg.softuni.playing_cards_shop.models.entities.AddressEntity;
import bg.softuni.playing_cards_shop.repositories.AddressRepository;
import bg.softuni.playing_cards_shop.services.interfaces.AddressService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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
        address.setUser(userService.getCurrentUser());
        address=this.addressRepository.save(address);

        this.userService.addAddress(name, address);
    }

    @Override
    public void deleteAddressById(Long id) {
        this.addressRepository.deleteById(id);
    }

    @Override
    public AddressEntity findAddressById(Long addressId) {
        return this.addressRepository.findById(addressId)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_ADDRESS));
    }

    @Override
    public boolean ownsAddress(String name, Long id) {
        return this.addressRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_ADDRESS))
                .getUser().getUsername().equals(name);
    }
}
