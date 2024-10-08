package bg.softuni.playing_cards_shop.services.interfaces;


import bg.softuni.playing_cards_shop.models.entities.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    String OBJECT_NAME_PICTURE="picture";

    PictureEntity getDefaultProfilePicture();

    PictureEntity getDefaultDistributorProfile();

    boolean validatePicture(MultipartFile picture);

    void deletePicture(PictureEntity picture);

    String getPictureUrl(PictureEntity picture);

    PictureEntity save(MultipartFile picture) throws IOException;
}
