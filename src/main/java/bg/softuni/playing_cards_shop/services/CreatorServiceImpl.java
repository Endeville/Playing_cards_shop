package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.models.entities.CreatorEntity;
import bg.softuni.playing_cards_shop.models.views.CreatorDetailsDto;
import bg.softuni.playing_cards_shop.repositories.CreatorRepository;
import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_CREATOR;

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
        if (this.pictureService.validatePicture(creatorDto.getPicture())) {
            var picture = this.pictureService.save(creatorDto.getPicture());
            creator.setPicture(picture);
        } else {
            creator.setPicture(this.pictureService.getDefaultProfilePicture());
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
    public CreatorEntity findByName(String creatorName) {
        return this.creatorRepository.findCreatorEntityByName(creatorName)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_CREATOR));
    }

    @Override
    public boolean nameExists(String name) {
        return this.creatorRepository.existsByName(name);
    }

    @Override
    public CreatorDetailsDto getCreatorDetailsByName(String name) {
        var creator = this.creatorRepository.findCreatorEntityByName(name)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_CREATOR));

        var result=this.modelMapper.map(creator, CreatorDetailsDto.class);
        result.setPicture(this.pictureService.getPictureUrl(creator.getPicture()));

        return result;
    }
}
