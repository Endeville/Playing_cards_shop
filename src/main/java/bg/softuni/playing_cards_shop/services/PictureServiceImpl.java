package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import bg.softuni.playing_cards_shop.repositories.PictureRepository;
import bg.softuni.playing_cards_shop.services.cloudinary.CloudinaryService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public PictureEntity getDefaultProfilePicture() {
        return this.pictureRepository.findPictureEntityByUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg")
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_PICTURE));
    }

    @Override
    public boolean validatePicture(MultipartFile picture) {
        return !picture.getOriginalFilename().trim().equals("");
    }

    @Override
    public void deletePicture(PictureEntity picture) {
        this.cloudinaryService.deleteImage(picture.getPublicId());

        this.pictureRepository.delete(picture);
    }

    @Override
    public String getPictureUrl(PictureEntity picture) {
        return picture.getUrl();
    }

    @Override
    public PictureEntity save(MultipartFile picture) throws IOException {
        final var uploaded = cloudinaryService.uploadImage(picture);
        return pictureRepository.save(this.modelMapper.map(uploaded, PictureEntity.class));
    }

    @Override
    public PictureEntity getDefaultDistributorProfile() {
        return this.pictureRepository.findPictureEntityByUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png")
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_PICTURE));
    }


}
