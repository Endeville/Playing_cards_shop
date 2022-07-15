package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.repositories.CreatorRepository;
import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CreatorServiceImpl implements CreatorService {

    private final CreatorRepository creatorRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public CreatorServiceImpl(CreatorRepository creatorRepository, PictureService pictureService, ModelMapper modelMapper) {
        this.creatorRepository = creatorRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCreator(AddCreatorDto creatorDto) throws IOException {
        var creator=this.modelMapper.map(creatorDto, CreatorEntity.class);
        var pictures=this.pictureService.saveAll(creatorDto.getPictures());
        creator.setPictures(pictures);

        this.creatorRepository.save(creator);
    }
}
