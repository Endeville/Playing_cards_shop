package bg.softuni.playing_cards_shop.services.interfaces;


import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface PictureService {
    Set<PictureEntity> saveAll(List<MultipartFile> pictures) throws IOException;

    List<String> getPicturesUrls(Set<PictureEntity> pictures);

    PictureEntity getDefaultProfilePicture();

    PictureEntity getDefaultDistributorProfile();

    boolean validatePictures(List<MultipartFile> pictures);
}
