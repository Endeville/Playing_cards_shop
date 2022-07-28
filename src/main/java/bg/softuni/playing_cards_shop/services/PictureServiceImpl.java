package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.cloudinary.CloudinaryImage;
import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import bg.softuni.playing_cards_shop.repositories.PictureRepository;
import bg.softuni.playing_cards_shop.services.cloudinary.CloudinaryService;
import bg.softuni.playing_cards_shop.services.interfaces.PictureService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.OBJECT_NAME_PICTURE;

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
    public Set<PictureEntity> saveAll(List<MultipartFile> pictures) throws IOException {

        var result=new HashSet<PictureEntity>();

        for (MultipartFile picture : pictures) {
            result.add(this.save(picture));
        }

        return result;
    }

    @Override
    public List<String> getPicturesUrls(Set<PictureEntity> pictures) {
        return pictures.stream()
                .map(this::getPictureUrl)
                .collect(Collectors.toList());
    }

    @Override
    public PictureEntity getDefaultProfilePicture() {
        return this.pictureRepository.findPictureEntityByUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg")
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_PICTURE));
    }

    @Override
    public boolean validatePictures(List<MultipartFile> pictures) {
        return !pictures.isEmpty() && !pictures.get(0).getOriginalFilename().trim().equals("");
    }

    @Override
    public void deletePictures(Set<PictureEntity> pictures) {
        pictures.stream()
                .map(PictureEntity::getPublicId)
                .forEach(this.cloudinaryService::deleteImage);

        this.pictureRepository.deleteAll(pictures);
    }

    @Override
    public String getPictureUrl(PictureEntity picture) {
        return picture.getUrl();
    }

    @Override
    public PictureEntity save(MultipartFile picture) throws IOException {
        final var uploaded = cloudinaryService.uploadImage(picture);
        return pictureRepository.save(new PictureEntity()
                .setUrl(uploaded.getUrl())
                .setPublicId(uploaded.getPublicId()));
    }

    @Override
    public PictureEntity getDefaultDistributorProfile() {
        return this.pictureRepository.findPictureEntityByUrl("https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png")
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_PICTURE));
    }


}
