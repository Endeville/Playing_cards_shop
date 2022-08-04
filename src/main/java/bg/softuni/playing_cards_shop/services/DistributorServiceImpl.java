package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddDistributorDto;
import bg.softuni.playing_cards_shop.models.entities.DistributorEntity;
import bg.softuni.playing_cards_shop.models.views.DistributorDetailsDto;
import bg.softuni.playing_cards_shop.repositories.DistributorRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DistributorService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistributorServiceImpl implements DistributorService {

    private final DistributorRepository distributorRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public DistributorServiceImpl(DistributorRepository distributorRepository, PictureService pictureService, ModelMapper modelMapper) {
        this.distributorRepository = distributorRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DistributorDetailsDto findDistributorDetailsByBrand(String brand) {
        var distributor = this.distributorRepository
                .findDistributorEntityByBrand(brand)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_DISTRIBUTOR));

        var result = this.modelMapper.map(distributor, DistributorDetailsDto.class);
        result.setPicture(this.pictureService.getPictureUrl(distributor.getPicture()));

        return result;
    }

    @Override
    public boolean brandExists(String brand) {
        return this.distributorRepository.existsByBrand(brand);
    }

    @Override
    public void addDistributor(AddDistributorDto distributorDto) throws IOException {
        var distributor = this.modelMapper.map(distributorDto, DistributorEntity.class);

        if (this.pictureService.validatePicture(distributorDto.getPicture())) {
            var pictures = pictureService.save(distributorDto.getPicture());
            distributor.setPicture(pictures);
        } else {
            distributor.setPicture(this.pictureService.getDefaultDistributorProfile());
        }

        this.distributorRepository.save(distributor);
    }

    @Override
    public List<String> getDistributorsNames() {
        return this.distributorRepository.findAll().stream()
                .map(DistributorEntity::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public DistributorEntity findDistributorByBrand(String distributorBrand) {
        return this.distributorRepository.findDistributorEntityByBrand(distributorBrand)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_DISTRIBUTOR));
    }
}
