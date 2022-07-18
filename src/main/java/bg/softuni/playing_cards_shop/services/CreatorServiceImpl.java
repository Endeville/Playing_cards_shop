package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.repositories.CreatorRepository;
import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        var creator = this.modelMapper.map(creatorDto, CreatorEntity.class);
        if (this.pictureService.validatePictures(creatorDto.getPictures())) {
            var pictures = this.pictureService.saveAll(creatorDto.getPictures());
            creator.setPictures(pictures);
        } else {
            creator.setPictures(Set.of(this.pictureService.getDefaultProfilePicture()));
        }


        //todo: add the creator for mod approval
        this.creatorRepository.save(creator);
    }

    @Override
    public List<String> getCreatorsNames() {
        return this.creatorRepository.findAll().stream()
                .map(CreatorEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CreatorEntity> findByName(String creatorName) {
        return this.creatorRepository.findCreatorEntityByName(creatorName);
    }

    @Override
    public boolean nameExists(String name) {
        return this.creatorRepository.existsByName(name);
    }
}
