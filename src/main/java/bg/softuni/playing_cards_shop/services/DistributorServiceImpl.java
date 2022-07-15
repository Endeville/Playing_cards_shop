package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddDistributorDto;
import bg.softuni.playing_cards_shop.models.entities.DistributorEntity;
import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import bg.softuni.playing_cards_shop.models.views.DistributorDetailsDto;
import bg.softuni.playing_cards_shop.repositories.DistributorRepository;
import bg.softuni.playing_cards_shop.services.interfaces.DistributorService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_DISTRIBUTOR;

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
    public DistributorDetailsDto findDistributorById(Long id) {
        var distributor= this.distributorRepository
                .findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_DISTRIBUTOR));

        var result=this.modelMapper.map(distributor, DistributorDetailsDto.class);
        result.setPictures(this.pictureService.getPicturesUrls(distributor.getPictures()));

        return result;
    }

    @Override
    public void addDistributor(AddDistributorDto distributorDto) throws IOException {
        var distributor=this.modelMapper.map(distributorDto, DistributorEntity.class);

        var pictures=pictureService.saveAll(distributorDto.getPictures());
        distributor.setPictures(pictures);

        this.distributorRepository.save(distributor);
    }
}
