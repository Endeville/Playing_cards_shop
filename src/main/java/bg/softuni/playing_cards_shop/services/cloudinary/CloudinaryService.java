package bg.softuni.playing_cards_shop.services.cloudinary;

import bg.softuni.playing_cards_shop.models.cloudinary.CloudinaryImage;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static bg.softuni.playing_cards_shop.constants.GlobalConstants.*;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public CloudinaryImage uploadImage(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary
                    .uploader()
                    .upload(tempFile, Collections.emptyMap());

            String url = uploadResult.getOrDefault(URL,
                    "");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImage()
                    .setUrl(url)
                    .setPublicId(publicId);

        } finally {
            tempFile.delete();
        }
    }

    public void deleteImage(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Collections.emptyMap());
        } catch (IOException ignored) {
        }
    }
}
